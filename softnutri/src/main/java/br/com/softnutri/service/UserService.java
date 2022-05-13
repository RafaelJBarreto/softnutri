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
import br.com.softnutri.config.security.payload.request.LoginRequest;
import br.com.softnutri.config.security.payload.request.SignupRequest;
import br.com.softnutri.config.security.payload.request.TokenRefreshRequest;
import br.com.softnutri.config.security.payload.response.JwtResponse;
import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.config.security.services.RefreshTokenService;
import br.com.softnutri.config.security.services.UserDetailsImpl;
import br.com.softnutri.config.security.services.UtilsServiceImpl;
import br.com.softnutri.domain.Module;
import br.com.softnutri.domain.Paper;
import br.com.softnutri.domain.Person;
import br.com.softnutri.domain.RefreshToken;
import br.com.softnutri.domain.User;
import br.com.softnutri.dto.ModuleDTO;
import br.com.softnutri.dto.UserDTO;
import br.com.softnutri.exception.TokenRefreshException;
import br.com.softnutri.repository.ModuleRepository;
import br.com.softnutri.repository.UserRepository;
import br.com.softnutri.util.Criptografia;

@Service(value = "usuarioService")
public class UserService{

	static final String ROLENOTFOUND = "Error: Papel is not found.";
	
	private final UserRepository userRepository;

	private final AuthenticationManager authenticationManager;

	private final JwtUtils jwtUtils;

	private final RefreshTokenService refreshTokenService;

	private final ModelMapper modelMapper;

	private final UtilsServiceImpl utils;
	
	private final ModuleRepository moduleRepository;

	@Autowired
	public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,
			JwtUtils jwtUtils, RefreshTokenService refreshTokenService, UtilsServiceImpl utils,
			ModelMapper modelMapper, ModuleRepository moduleRepository) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.refreshTokenService = refreshTokenService;
		this.modelMapper = modelMapper;
		this.utils = utils;
        this.moduleRepository = moduleRepository;
	}

	public User updateUsuario(User usuario) {
		if (usuario.getPassword().isBlank()) {
			String password = this.userRepository.getSenhaByIdPessoa(usuario.getIdPerson());
			usuario.setPassword(password);
		}
		return this.userRepository.save(usuario);
	}

	public ResponseEntity<MessageResponse> save(SignupRequest signUpRequest) {

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("GLOBAL.MSG_EMAIL_ALREADY"));
		}

		User usuario = new User();
		usuario.setEmail(signUpRequest.getUsername());
		usuario.setPassword(signUpRequest.getPassword());
		usuario.setLanguage(signUpRequest.getLanguage());

		Set<String> strPapels = signUpRequest.getRole();
		Set<Paper> papels = new HashSet<>();

		if (strPapels == null) {
			Paper usuarioPapel = new Paper();

			papels.add(usuarioPapel);
		}

		usuario.setPapel(papels);
		this.userRepository.save(usuario);
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
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
		Optional<Person> resultUsuario = refreshTokenService.findByToken(requestRefreshToken)
				.map(refreshTokenService::verifyExpiration).map(RefreshToken::getPerson);
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
		List<Module> modules = moduleRepository.findModuleByIdPessoa(usuarioDetails.getId());
				
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(usuarioDetails.getId());
		jwt.setLanguage(languageUsuario);
		jwt.setRefreshToken(refreshToken.getToken());
		jwt.setRoles(papels);
		jwt.setModules(ModuleDTO.converter(modules));
		return jwt;
	}

	
	public String getLanguageUsuario(Long idPessoa) {
		return this.userRepository.findIdiomaByIdPessoa(idPessoa);
	}

	/**
	 * @param logOutRequest
	 * @return
	 */
	public ResponseEntity<MessageResponse> logOut(User u) {
		refreshTokenService.deleteByUsuarioId(u);
		return ResponseEntity.ok(new MessageResponse("Log out successful!"));
	}


	/**
	 * @param idUsuario
	 * @return
	 */
	public ResponseEntity<UserDTO> findById(Long idUsuario) {
		UserDTO resultDto = null;
		try {
			resultDto = modelMapper.map(this.userRepository.findById(idUsuario), UserDTO.class);

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
	public ResponseEntity<UserDTO> updateUser(UserDTO usuario) {
		UserDTO resultDTO = null;
		try {
			if (utils.verifyUserLogged(usuario.getEmail())) {
				User usuarioParam = modelMapper.map(usuario, User.class);
				if (!usuarioParam.getPassword().isBlank()) {
					usuarioParam.setPassword(usuarioParam.getPassword());
				}
				resultDTO = modelMapper.map(this.updateUsuario(usuarioParam), UserDTO.class);
				return new ResponseEntity<>(resultDTO, HttpStatus.OK);
			}

			return new ResponseEntity<>(resultDTO, HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity<>(resultDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public User getUserLogged() {
		Authentication principal = SecurityContextHolder.getContext().getAuthentication();  
		User user = this.userRepository.findByEmail(principal.getName()).get();
			return user; 
	}
}
