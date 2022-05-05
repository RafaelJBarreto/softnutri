package br.com.softnutri.config.security.jwt;

import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import br.com.softnutri.config.security.payload.response.JwtResponse;
import br.com.softnutri.config.security.services.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${softnutri.app.jwtSecret}")
	private String jwtSecret;

	@Value("${softnutri.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	@Value("${softnutri.app.authorities.key}")
	private String authoritiesKey;

	public String generateJwtToken(UserDetailsImpl userPrincipal) {
		return generateTokenFromUsername(userPrincipal.getUsername());
	}

	public String generateTokenFromUsername(String username) {
        Date expiration =  new Date((new Date()).getTime() + jwtExpirationMs);
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	 public JwtResponse generateToken(Authentication authentication) {
         String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

         JwtResponse response = new JwtResponse();
         Date expiration =  new Date((new Date()).getTime() + jwtExpirationMs);
         response.setToken(Jwts.builder()
                .setSubject(authentication.getName())
                .claim(authoritiesKey, authorities)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact());
         response.setExpiration(expiration); 
         return response;
	 }

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

}
