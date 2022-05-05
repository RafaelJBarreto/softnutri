package br.com.softnutri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.softnutri.dto.UsuarioDTO;
import br.com.softnutri.security.payload.request.LogOutRequest;
import br.com.softnutri.security.payload.request.LoginRequest;
import br.com.softnutri.security.payload.request.SignupRequest;
import br.com.softnutri.security.payload.request.TokenRefreshRequest;
import br.com.softnutri.security.payload.response.JwtResponse;
import br.com.softnutri.security.payload.response.MessageResponse;
import br.com.softnutri.security.payload.response.TokenRefreshResponse;
import br.com.softnutri.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioController {

	static final String ROLENOTFOUND = "Error: Papel is not found.";

	private final UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/findId")
	public ResponseEntity<UsuarioDTO> getFindById(@RequestParam Long idUsuario) {
		return usuarioService.findById(idUsuario);
	}

	@PutMapping("/updateUsuario")
	public ResponseEntity<UsuarioDTO> updateUsuario(@RequestBody UsuarioDTO usuario) {
		return usuarioService.updateUser(usuario);
	}

	@PostMapping("/auth/signin")
	public ResponseEntity<JwtResponse> authenticateUsuario(@Valid @RequestBody LoginRequest loginRequest) {
		return usuarioService.autenticacao(loginRequest);
	}

	@PostMapping("/auth/signup")
	public ResponseEntity<MessageResponse> registerUsuario(@Valid @RequestBody SignupRequest signUpRequest) {
		return usuarioService.save(signUpRequest);
	}

	@PostMapping("/auth/refreshtoken")
	public ResponseEntity<TokenRefreshResponse> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
		return usuarioService.refreshToken(request);
	}

	@PostMapping("/auth/logout")
	public ResponseEntity<MessageResponse> logoutUsuario(@Valid @RequestBody LogOutRequest logOutRequest) {
		return usuarioService.logOut(logOutRequest);
	}

}
