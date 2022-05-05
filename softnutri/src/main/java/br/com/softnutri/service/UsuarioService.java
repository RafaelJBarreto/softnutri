package br.com.softnutri.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.jwt.JwtUtils;
import br.com.softnutri.config.security.payload.request.LogOutRequest;
import br.com.softnutri.config.security.payload.request.LoginRequest;
import br.com.softnutri.config.security.payload.request.SignupRequest;
import br.com.softnutri.config.security.payload.request.TokenRefreshRequest;
import br.com.softnutri.config.security.payload.response.JwtResponse;
import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.config.security.services.RefreshTokenService;
import br.com.softnutri.config.security.services.UserDetailsImpl;
import br.com.softnutri.config.security.services.UtilsServiceImpl;
import br.com.softnutri.dominio.Papel;
import br.com.softnutri.dominio.Pessoa;
import br.com.softnutri.dominio.RefreshToken;
import br.com.softnutri.dominio.Usuario;
import br.com.softnutri.dto.UsuarioDTO;
import br.com.softnutri.exception.TokenRefreshException;
import br.com.softnutri.repository.UsuarioRepository;
import br.com.softnutri.util.Criptografia;

@Service(value = "usuarioService")
public class UsuarioService{

	static final String ROLENOTFOUND = "Error: Papel is not found.";
	
	private final UsuarioRepository usuarioRepository;

	private final AuthenticationManager authenticationManager;

	private final JwtUtils jwtUtils;

	private final RefreshTokenService refreshTokenService;

	private final ModelMapper modelMapper;

	private final UtilsServiceImpl utils;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository, AuthenticationManager authenticationManager,
			JwtUtils jwtUtils, RefreshTokenService refreshTokenService, UtilsServiceImpl utils,
			ModelMapper modelMapper) {
		this.usuarioRepository = usuarioRepository;
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.refreshTokenService = refreshTokenService;
		this.modelMapper = modelMapper;
		this.utils = utils;
	}

	public Usuario updateUsuario(Usuario usuario) {
		if (usuario.getSenha().isBlank()) {
			String password = this.usuarioRepository.getSenhaByIdPessoa(usuario.getIdPessoa());
			usuario.setSenha(password);
		}
		return this.usuarioRepository.save(usuario);
	}

	public ResponseEntity<MessageResponse> save(SignupRequest signUpRequest) {

		if (usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("GLOBAL_WORD.MSG_EMAIL_ALREADY"));
		}

		Usuario usuario = new Usuario();
		usuario.setEmail(signUpRequest.getUsername());
		usuario.setSenha(signUpRequest.getPassword());
		usuario.setIdioma(signUpRequest.getLanguage());

		Set<String> strPapels = signUpRequest.getRole();
		Set<Papel> papels = new HashSet<>();

		if (strPapels == null) {
			Papel usuarioPapel = new Papel();

			papels.add(usuarioPapel);
		}

		usuario.setPapel(papels);
		this.usuarioRepository.save(usuario);
		return ResponseEntity.ok(new MessageResponse("GLOBAL_WORD.MSG_USER_CREATE_SUCCESS"));
	}

	public ResponseEntity<JwtResponse> autenticacao(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(Criptografia.encode(loginRequest.getUsername()), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		JwtResponse jwt = gerarTokens(authentication);
		return ResponseEntity.ok(jwt);
	}
	
	/**
	 * @param request
	 * @return
	 * @throws TokenRefreshException
	 */
	public ResponseEntity<JwtResponse> refreshToken(TokenRefreshRequest request) throws TokenRefreshException {
		String requestRefreshToken = request.getRefreshToken();
		Optional<Pessoa> resultUsuario = refreshTokenService.findByToken(requestRefreshToken)
				.map(refreshTokenService::verifyExpiration).map(RefreshToken::getPessoa);
		if (resultUsuario.isPresent()) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			JwtResponse jwt = gerarTokens(authentication);
			return ResponseEntity.ok(jwt);
		}

		throw new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!");
	}

	/**
	 * @param authentication
	 * @return JwtResponse
	 */
	private JwtResponse gerarTokens(Authentication authentication) {
		UserDetailsImpl usuarioDetails = (UserDetailsImpl) authentication.getPrincipal();
		String languageUsuario = this.getLanguageUsuario(usuarioDetails.getId());
		JwtResponse jwt = jwtUtils.generateToken(authentication);
		List<String> papels = usuarioDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.toList();
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(usuarioDetails.getId());
		jwt.setLanguage(languageUsuario);
		jwt.setRefreshToken(refreshToken.getToken());
		jwt.setRoles(papels);
		return jwt;
	}

	
	public String getLanguageUsuario(Long idPessoa) {
		return this.usuarioRepository.findIdiomaByIdPessoa(idPessoa);
	}

	/**
	 * @param logOutRequest
	 * @return
	 */
	public ResponseEntity<MessageResponse> logOut(LogOutRequest logOutRequest) {
		refreshTokenService.deleteByUsuarioId(logOutRequest.getUserId());
		return ResponseEntity.ok(new MessageResponse("Log out successful!"));
	}


	/**
	 * @param idUsuario
	 * @return
	 */
	public ResponseEntity<UsuarioDTO> findById(Long idUsuario) {
		UsuarioDTO resultDto = null;
		try {
			resultDto = modelMapper.map(this.usuarioRepository.findById(idUsuario), UsuarioDTO.class);

			if (resultDto != null) {
				return new ResponseEntity<>(resultDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @param usuario
	 * @return
	 */
	public ResponseEntity<UsuarioDTO> updateUser(UsuarioDTO usuario) {
		UsuarioDTO resultDTO = null;
		try {
			if (utils.verifyUserLogged(usuario.getEmail())) {
				Usuario usuarioParam = modelMapper.map(usuario, Usuario.class);
				if (!usuarioParam.getSenha().isBlank()) {
					usuarioParam.setSenha(usuarioParam.getSenha());
				}
				resultDTO = modelMapper.map(this.updateUsuario(usuarioParam), UsuarioDTO.class);
				return new ResponseEntity<>(resultDTO, HttpStatus.OK);
			}

			return new ResponseEntity<>(resultDTO, HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity<>(resultDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
 
}
