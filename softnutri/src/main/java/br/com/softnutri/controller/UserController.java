package br.com.softnutri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.softnutri.config.security.payload.request.LoginRequest;
import br.com.softnutri.config.security.payload.request.TokenRefreshRequest;
import br.com.softnutri.config.security.payload.response.JwtResponse;
import br.com.softnutri.config.security.payload.response.MessageResponse;
import br.com.softnutri.dto.UserDTO;
import br.com.softnutri.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/findId")
	public ResponseEntity<UserDTO> getFindById(@RequestParam Long idUsuario) {
		return ResponseEntity.ok(this.userService.getUser(idUsuario));
	}

	@PutMapping("/updateUsuario")
	public ResponseEntity<UserDTO> updateUsuario(@RequestBody UserDTO usuario) {
		return userService.updateUser(usuario);
	}

	@PostMapping("/auth/signin")
	public ResponseEntity<JwtResponse> authenticateUsuario(@Valid @RequestBody LoginRequest loginRequest) {
		return userService.autenticacao(loginRequest);
	}

	@PostMapping("/auth/refreshtoken")
	public ResponseEntity<JwtResponse> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
		return userService.refreshToken(request);
	}

	@DeleteMapping("/auth/logout")
	public ResponseEntity<MessageResponse> logoutUsuario() {
		return userService.logOut(userService.getUserLogged());
	}

}
