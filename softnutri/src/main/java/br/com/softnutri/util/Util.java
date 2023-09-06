package br.com.softnutri.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import br.com.softnutri.domain.PersonPaper;

public class Util {

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
	public static int getGet(String permission) {
		if(permission != null)
			return permission.contains("get") ? 1 : 0;
		else
			return -1;
		
	}
	
	public static int getPost(String permission) {
		if(permission != null)
			return permission.contains("post") ? 1 : 0;
		else
			return -1;
		
	}
	
	public static int getPut(String permission) {
		if(permission != null)
			return permission.contains("put") ? 1 : 0;
		else
			return -1;
		
	}
	
	public static int getDelete(String permission) {
		if(permission != null)
			return permission.contains("delete") ? 1 : 0;
		else
			return -1;
		
	}

	public static String getRole(PersonPaper paper) {
		StringBuilder permissoes = new StringBuilder();
		if(paper.getGet() == 1) {
			permissoes.append("ROLE_" + paper.getPaper().getDescription().toUpperCase() + "_GET");
		}
		
		if(paper.getPost() == 1) {
			permissoes.append(", ROLE_" + paper.getPaper().getDescription().toUpperCase() + "_POST");
		}
		
		if(paper.getPut() == 1) {
			permissoes.append(", ROLE_" + paper.getPaper().getDescription().toUpperCase() + "_PUT");
		}
		
		if(paper.getDelete() == 1) {
			permissoes.append(", ROLE_" + paper.getPaper().getDescription().toUpperCase() + "_DELETE");
		}
		
		return permissoes.toString();
	}
}
