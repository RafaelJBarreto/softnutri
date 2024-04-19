package br.com.softnutri.util;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import br.com.softnutri.domain.PersonPaper;

public final class Util {
	
	private static final String ROLE = "ROLE_";

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		final Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
	public static int getGet(String permission) {
		if(permission != null) {
			return permission.contains("get") ? 1 : 0;
		} else {
			return -1;
		}
		
	}
	
	public static int getPost(String permission) {
		if(permission != null) {
			return permission.contains("post") ? 1 : 0;
		} else {
			return -1;
		}
		
	}
	
	public static int getPut(String permission) {
		if(permission != null) {
			return permission.contains("put") ? 1 : 0;
		} else {
			return -1;
		}
		
	}
	
	public static int getDelete(String permission) {
		if(permission != null) {
			return permission.contains("delete") ? 1 : 0;
		} else {
			return -1;
		}
		
	}

	public static String getRole(PersonPaper paper) {
		final StringBuilder permissoes = new StringBuilder();
		if(paper.getGet() == 1) {
			permissoes.append(ROLE + paper.getPaper().getDescription().toUpperCase() + "_GET");
		}
		
		if(paper.getPost() == 1) {
			permissoes.append(", " + ROLE + paper.getPaper().getDescription().toUpperCase() + "_POST");
		}
		
		if(paper.getPut() == 1) {
			permissoes.append(", " + ROLE + paper.getPaper().getDescription().toUpperCase() + "_PUT");
		}
		
		if(paper.getDelete() == 1) {
			permissoes.append(", " + ROLE + paper.getPaper().getDescription().toUpperCase() + "_DELETE");
		}
		
		return permissoes.toString();
	}
	
	public static String[] separateRoles(String roles) {
		final StringTokenizer tokenizer = new StringTokenizer(roles, ",");
        final int tokenCount = tokenizer.countTokens();
        final String[] stringArray = new String[tokenCount];

        for (int i = 0; i < tokenCount; i++) {
            stringArray[i] = tokenizer.nextToken();
        }
        
        return stringArray;
	}
	
	
	public static float convertHour(LocalTime hour) {
		return hour.get(ChronoField.MINUTE_OF_DAY) / 60f;
	}
}
