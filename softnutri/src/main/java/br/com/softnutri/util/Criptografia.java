package br.com.softnutri.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class Criptografia {

	protected Criptografia() {
	}

	public static String encode(String encodedString) {
		return new String(Base64.encodeBase64(encodedString.getBytes()));
	}

	public static String decode(String encodedString) {
		return new String(Base64.decodeBase64(encodedString.getBytes()));
	}

	public static String encoderSecurity(String senha) {
		return passwordEncoder().encode(senha);
	}

	private static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
