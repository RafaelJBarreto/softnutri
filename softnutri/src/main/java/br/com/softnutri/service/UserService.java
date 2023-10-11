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
import br.com.softnutri.enuns.UserType;
import br.com.softnutri.exception.SoftNutriException;
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

	public ResponseEntity<UserDTO> findById(Long idUsuario) throws SoftNutriException {
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
			throw new SoftNutriException("Error find by id User " + e.getMessage(), e);
		}
	}

	public ResponseEntity<MessageResponse> save(User user) throws SoftNutriException {

		try {
			this.userRepository.save(user);
			return ResponseEntity.ok(new MessageResponse("GLOBAL.MSG_CREATE_SUCCESS"));

		} catch (Exception e) {
			throw new SoftNutriException("Error save User " + e.getMessage(), e);
		}
	}
	
	public UserDTO getUser(Long idPerson) throws SoftNutriException {
		try {
			Optional<User> user = this.userRepository.findById(idPerson);
			if (user.isPresent()) {
				return UserDTO.converter(user.get());
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new SoftNutriException("Error get User " + e.getMessage(), e);
		}
	}
	
	public User getUserById(Long idPerson) throws SoftNutriException {
		try {
			Optional<User> user = this.userRepository.findById(idPerson);
			if (user.isPresent()) {
				return user.get();
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new SoftNutriException("Error get User By Id " + e.getMessage(), e);
		}
	}

	public List<UserDTO> getUsers(String name) throws SoftNutriException {
		try {
			if (name == null) {
				return UserDTO.converter(userRepository.findAll());
			}
			return Collections.emptyList();
		} catch (Exception e) {
			throw new SoftNutriException("Error get Users " + e.getMessage(), e);
		}

	}
	
	public List<UserDTO> getPatients() throws SoftNutriException {
		try {
			return UserDTO.converter(userRepository.findByUserType(UserType.PATIENT));
		} catch (Exception e) {
			throw new SoftNutriException("Error get Patients " + e.getMessage(), e);
		}

	}
	
	public List<UserDTO> getProfessional() throws SoftNutriException {
		try {
			return UserDTO.converter(userRepository.getProfessional(UserType.PATIENT));
		} catch (Exception e) {
			throw new SoftNutriException("Error get Professional " + e.getMessage(), e);
		}

	}
	
	public List<UserDTO> getNutritionist() throws SoftNutriException {
		try {
			return UserDTO.converter(userRepository.getNutritionist(UserType.NUTRITIONIST));
		} catch (Exception e) {
			throw new SoftNutriException("Error get Nutritionist " + e.getMessage(), e);
		}

	}
}
