package br.com.softnutri.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.jwt.JwtUtils;
import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.config.security.services.RefreshTokenService;
import br.com.softnutri.config.security.services.UtilsServiceImpl;
import br.com.softnutri.domain.User;
import br.com.softnutri.dto.UserDTO;
import br.com.softnutri.repository.ModuleRepository;
import br.com.softnutri.repository.UserRepository;

@Service(value = "usuarioService")
public class UserService extends AutenticationService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils,
			RefreshTokenService refreshTokenService, UtilsServiceImpl utils, ModuleRepository moduleRepository) {
		super(userRepository, authenticationManager, jwtUtils, refreshTokenService, utils, moduleRepository);
		this.userRepository = userRepository;
	}

	public ResponseEntity<UserDTO> findById(Long idUsuario) {
		UserDTO resultDto = null;
		try {
			Optional<User> user = this.userRepository.findById(idUsuario);
			if (user.isPresent()) {
				resultDto = UserDTO.converter(user.get());
				return new ResponseEntity<>(resultDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<MessageResponse> save(User user) {

		try {
			this.userRepository.save(user);
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));

		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse(e.getMessage()));
		}
	}

	public UserDTO getUser(Long idPerson) {
		Optional<User> user = this.userRepository.findById(idPerson);
		if (user.isPresent()) {
			return UserDTO.converter(user.get());
		} else {
			return null;
		}
	}

	public List<UserDTO> getUsers(String name) {
		if (name == null) {
			List<User> users = userRepository.findAll();
			return UserDTO.converter(users);
		}
		return Collections.emptyList();

	}
}
