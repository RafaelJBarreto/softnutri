package br.com.softnutri.config.security.payload.response;

import java.util.Date;
import java.util.List;

import br.com.softnutri.records.ModuleDTO;

public record JwtResponse(String email, String token, String type, String refreshToken, String language, Date expiration,
		List<String> roles, List<ModuleDTO> modules) {

}
