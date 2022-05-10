package br.com.softnutri.config.security.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softnutri.domain.Person;
import br.com.softnutri.domain.RefreshToken;
import br.com.softnutri.domain.User;
import br.com.softnutri.exception.TokenRefreshException;
import br.com.softnutri.repository.RefreshTokenRepository;
import br.com.softnutri.repository.UserRepository;
 

@Service
public class RefreshTokenService {

	@Value("${softnutri.app.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository usuarioRepository;

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken createRefreshToken(Long idPessoa) {
		RefreshToken refreshToken = new RefreshToken();
		User u = usuarioRepository.findById(idPessoa).orElseGet(User::new);

		refreshToken.setPerson(u);
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());
		if(validRefreshTokenByPessoa(u))
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
	public boolean validRefreshTokenByPessoa(Person pessoa) {
		Optional<RefreshToken> rT=refreshTokenRepository.findByPerson(pessoa);
		if(rT.isPresent() ){
			refreshTokenRepository.delete(rT.get());
		}
			return true;
	}

	@Transactional
	public int deleteByUsuarioId(Long usuarioId) {
		User u = usuarioRepository.findById(usuarioId).orElseGet(User::new);
		return refreshTokenRepository.deleteByPerson(u);
	}

}
