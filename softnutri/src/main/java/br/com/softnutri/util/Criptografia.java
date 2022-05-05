package br.com.softnutri.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Criptografia {

	protected Criptografia() {
		super();
	}
	@Autowired
	private static PasswordEncoder passEncoder;

	public static String encode(String encodedString) {
		return new String(Base64.encodeBase64(encodedString.getBytes()));
	}
	

	public static String decode(String encodedString) {
		return new String(Base64.decodeBase64(encodedString.getBytes()));
	}

	public static String encoderSecurity(String senha) {
		return passEncoder.encode(senha);
	}

}
