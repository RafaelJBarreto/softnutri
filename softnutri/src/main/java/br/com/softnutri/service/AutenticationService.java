package br.com.softnutri.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.softnutri.config.security.jwt.JwtUtils;
import br.com.softnutri.config.security.payload.request.dto.LoginRequest;
import br.com.softnutri.config.security.payload.request.dto.SignupRequest;
import br.com.softnutri.config.security.payload.request.dto.TokenRefreshRequest;
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
import br.com.softnutri.dto.UserDTO;
import br.com.softnutri.dto.prototype.UserPrototype;
import br.com.softnutri.exception.TokenRefreshException;
import br.com.softnutri.records.ModuleDTO;
import br.com.softnutri.repository.ModuleRepository;
import br.com.softnutri.repository.UserRepository;
import br.com.softnutri.util.Criptografia;

public class AutenticationService {

	static final String ROLENOTFOUND = "Error: Papel is not found.";

	private final UserRepository userRepository;

	private final AuthenticationManager authenticationManager;

	private final JwtUtils jwtUtils;

	private final RefreshTokenService refreshTokenService;

	private final UtilsServiceImpl utils;

	private final ModuleRepository moduleRepository;

	@Autowired
	public AutenticationService(UserRepository userRepository, AuthenticationManager authenticationManager,
			JwtUtils jwtUtils, RefreshTokenService refreshTokenService, UtilsServiceImpl utils,
			ModuleRepository moduleRepository) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.refreshTokenService = refreshTokenService;
		this.utils = utils;
		this.moduleRepository = moduleRepository;
	}

	public User updateUsuario(User usuario) {
		if (usuario.getPassword().isBlank()) {
			usuario.setPassword(this.userRepository.getSenhaByIdPessoa(usuario.getIdPerson()));
		}
		return this.userRepository.save(usuario);
	}

	public ResponseEntity<MessageResponse> save(SignupRequest signUpRequest) {

		if (userRepository.existsByEmail(signUpRequest.email())) {
			return ResponseEntity.badRequest().body(new MessageResponse("GLOBAL.MSG_EMAIL_ALREADY"));
		}

		final User usuario = new User();
		usuario.setEmail(signUpRequest.username());
		usuario.setPassword(signUpRequest.password());
		usuario.setLanguage(signUpRequest.language());

		final Set<String> strPapels = signUpRequest.role();
		final Set<Paper> papels = new HashSet<>();

		if (strPapels == null) {
			final Paper usuarioPapel = new Paper();
			papels.add(usuarioPapel);
		}

		this.userRepository.save(usuario);
		return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));
	}

	public ResponseEntity<JwtResponse> autenticacao(LoginRequest loginRequest) {
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(Criptografia.encode(loginRequest.username()),
						loginRequest.password()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return ResponseEntity.ok(gerarTokens(authentication));
	}

	/**
	 * @param request
	 * @return
	 * @throws TokenRefreshException
	 */
	public ResponseEntity<JwtResponse> refreshToken(TokenRefreshRequest request) {
		final String requestRefreshToken = request.refreshToken();
		final Optional<Person> resultUsuario = refreshTokenService.findByToken(requestRefreshToken)
				.map(refreshTokenService::verifyExpiration).map(RefreshToken::getPerson);
		if (resultUsuario.isPresent()) {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return ResponseEntity.ok(gerarTokens(authentication));
		}

		throw new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!");
	}

	/**
	 * @param authentication
	 * @return JwtResponse
	 */
	private JwtResponse gerarTokens(Authentication authentication) {
		final UserDetailsImpl usuarioDetails = (UserDetailsImpl) authentication.getPrincipal();
		final String languageUsuario = this.getLanguageUsuario(usuarioDetails.getId());
		final JwtResponse jwt = jwtUtils.generateToken(authentication);
		final List<String> papels = usuarioDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.toList();
		final List<Module> modules = moduleRepository.findModuleByIdPessoa(usuarioDetails.getId());

		final RefreshToken refreshToken = refreshTokenService.createRefreshToken(usuarioDetails.getId());
		return new JwtResponse(jwt.token(), jwt.type(), refreshToken.getToken(), languageUsuario, jwt.expiration(),
				papels,
				modules.stream().map(
						o -> new ModuleDTO(o.getIdModule(), o.getName(), o.getPathBase(), o.getIcon(), o.getOrders()))
						.toList());
	}

	public String getLanguageUsuario(Long idPessoa) {
		return this.userRepository.findIdiomaByIdPessoa(idPessoa);
	}

	/**
	 * @param logOutRequest
	 * @return
	 */
	public ResponseEntity<MessageResponse> logOut(User u) {
		this.refreshTokenService.deleteByUsuarioId(u);
		return ResponseEntity.ok(new MessageResponse("Log out successful!"));
	}

	/**
	 * @param usuario
	 * @return
	 */
	public ResponseEntity<UserDTO> updateUser(UserDTO usuario) {
		UserDTO resultDTO = null;
		try {
			if (utils.verifyUserLogged(usuario.getEmail())) {
				final User usuarioParam = UserPrototype.getUser(usuario, null);
				if (!usuarioParam.getPassword().isBlank()) {
					usuarioParam.setPassword(usuarioParam.getPassword());
				}
				resultDTO = UserDTO.converter(this.updateUsuario(usuarioParam));
				return new ResponseEntity<>(resultDTO, HttpStatus.OK);
			}

			return new ResponseEntity<>(resultDTO, HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity<>(resultDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public User getUserLogged() {
		final Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		final Optional<User> user = this.userRepository.findByEmail(principal.getName());
		return user.isPresent() ? user.get() : null;
	}

}
