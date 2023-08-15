package br.com.softnutri.interfaces;

public interface PermissionReturn {

	Long getIdPaper();
	String getDescription();
	String getAccess();
	String getSend();
	String getAlterar();
	String getRemove();
	int getRegister();
	int getPost();
	int getPut();
	int getDeletar();
	Long getIdModule();
	String getName();
	
}
