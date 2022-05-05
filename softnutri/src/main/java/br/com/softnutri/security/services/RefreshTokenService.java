package br.com.softnutri.security.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softnutri.dominio.RefreshToken;
import br.com.softnutri.dominio.Usuario;
import br.com.softnutri.exception.TokenRefreshException;
import br.com.softnutri.repository.RefreshTokenRepository;
import br.com.softnutri.repository.UsuarioRepository;
 

@Service
public class RefreshTokenService {

	@Value("${softnutri.app.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken createRefreshToken(Long usuarioId) {
		RefreshToken refreshToken = new RefreshToken();
		Usuario u = usuarioRepository.findById(usuarioId).orElseGet(Usuario::new);

		refreshToken.setPessoa(u);
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());

		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getToken(),
					"Refresh token was expired. Please make a new signin request");
		}

		return token;
	}

	@Transactional
	public int deleteByUsuarioId(Long usuarioId) {
		Usuario u = usuarioRepository.findById(usuarioId).orElseGet(Usuario::new);
		return refreshTokenRepository.deleteByPessoa(u);
	}

}
