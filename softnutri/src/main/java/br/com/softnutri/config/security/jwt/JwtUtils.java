package br.com.softnutri.config.security.jwt;

import java.security.KeyPair;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import br.com.softnutri.config.security.payload.response.JwtResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${softnutri.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	@Value("${softnutri.app.authorities.key}")
	private String authoritiesKey;
	
	private final KeyPair keys = Keys.keyPairFor(SignatureAlgorithm.RS512);

	public String generateTokenFromUsername(Authentication authentication, Date expiration) {
		
		 String authorities = authentication.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.joining(","));
		 Map<String, Object> claims = new HashMap<>();
		 claims.put("id", authentication.getName());
		 claims.put("roles", authorities);
		 
        return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(new Date())
        .setExpiration(expiration)
        .signWith(keys.getPrivate())
        .setSubject(authentication.getName())
        .compact();
	}
	 public JwtResponse generateToken(Authentication authentication) {

         JwtResponse response = new JwtResponse();
         Date expiration =  new Date((new Date()).getTime() + jwtExpirationMs);
         String token = generateTokenFromUsername(authentication, expiration);
         response.setToken(token);
         response.setExpiration(expiration); 
         return response;
	 }

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parserBuilder().setSigningKey(keys.getPrivate()).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(keys.getPublic()).build().parseClaimsJws(authToken).getBody();
			return true;
		} catch (Exception e) {
			LOGGER.error("Invalid JWT signature: {}", e.getLocalizedMessage());
			return false;
		}

		
	}
	
}
