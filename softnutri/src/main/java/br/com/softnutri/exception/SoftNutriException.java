package br.com.softnutri.exception;

import org.hibernate.exception.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("serial")
@Slf4j
public class SoftNutriException extends RuntimeException {
	
	private static final String CAUSE = "Cause: ";
	
	public SoftNutriException(String message, Throwable cause) {
		 super(message, cause);
		 final Throwable causeA = cause.getCause();
		 if (causeA instanceof ConstraintViolationException) {
			 final ConstraintViolationException sqlEx = (ConstraintViolationException) cause.getCause();
		     log.error(super.getMessage() + CAUSE + sqlEx.getSQLException().getLocalizedMessage() + " Error code: " + sqlEx.getErrorCode());     
		 }else {
			 log.error(super.getMessage() + CAUSE + cause.getMessage());     
		 }
    }
	
	public SoftNutriException(String message) {
		 super(message);
   }
}
