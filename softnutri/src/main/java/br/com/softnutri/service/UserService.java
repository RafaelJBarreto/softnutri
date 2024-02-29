package br.com.softnutri.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import br.com.softnutri.config.security.jwt.JwtUtils;
import br.com.softnutri.config.security.services.RefreshTokenService;
import br.com.softnutri.config.security.services.UtilsServiceImpl;
import br.com.softnutri.domain.User;
import br.com.softnutri.dto.UserDTO;
import br.com.softnutri.enuns.UserType;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.repository.ModuleRepository;
import br.com.softnutri.repository.UserRepository;

@Service
public class UserService extends AutenticationService {

	private static final String MESSAGE = "GLOBAL.ERROR_GET";
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils,
			RefreshTokenService refreshTokenService, UtilsServiceImpl utils, ModuleRepository moduleRepository) {
		super(userRepository, authenticationManager, jwtUtils, refreshTokenService, utils, moduleRepository);
		this.userRepository = userRepository;
	}

	public UserDTO getUser(Long idUsuario) {
		try {
			final Optional<User> user = this.userRepository.findById(idUsuario);
			if (user.isPresent()) {
				return UserDTO.converter(user.get());
			}else {
				throw new SoftNutriException(MESSAGE);
			}

		} catch (Exception e) {
			throw new SoftNutriException(MESSAGE, e);
		}
	}

	public void save(User user) {
		try {
			this.userRepository.save(user);
		} catch (Exception e) {
			throw new SoftNutriException("GLOBAL.ERROR_SAVE" + e.getMessage(), e);
		}
	}
	
	public User getUserById(Long idPerson) {
		try {
			final Optional<User> user = this.userRepository.findById(idPerson);
			if (user.isPresent()) {
				return user.get();
			} else {
				throw new SoftNutriException(MESSAGE);
			}
		} catch (Exception e) {
			throw new SoftNutriException(MESSAGE, e);
		}
	}

	public List<UserDTO> getUsers(String name) {
		try {
			if (name == null) {
				return UserDTO.converter(userRepository.findAll());
			}
			return Collections.emptyList();
		} catch (Exception e) {
			throw new SoftNutriException("GLOBAL.ERROR_LIST_ALL" + e.getMessage(), e);
		}

	}
	
	public List<UserDTO> getPatients() {
		try {
			return UserDTO.converter(userRepository.getUser(UserType.PATIENT));
		} catch (Exception e) {
			throw new SoftNutriException("PATIENT.ERROR_LIST_PATIENT", e);
		}

	}
	
	public List<UserDTO> getProfessional() {
		try {
			return UserDTO.converter(userRepository.getUser(UserType.NUTRITIONIST));
		} catch (Exception e) {
			throw new SoftNutriException("PROFESSIONAL.ERROR_LIST_PROFESSIONAL", e);
		}

	}
	
}
