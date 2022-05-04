package br.com.softnutri.util;

import org.apache.commons.codec.binary.Base64;

public class Security {
	
	
	public static String encode(String encodedString) {
		return new String(Base64.encodeBase64(encodedString.getBytes()));
	}
	
	public static String decode(String encodedString) {
		return new String(Base64.decodeBase64(encodedString.getBytes()));
	}

}
