package br.com.softnutri.exception;

import org.hibernate.exception.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("serial")
@Slf4j
public class SoftNutriException extends RuntimeException {
	
	private static final String CAUSE = "Cause: ";
	
	public SoftNutriException(String message, Throwable cause) {
		 super(message, cause);
		 Throwable causeA = cause.getCause();
		 if (causeA instanceof ConstraintViolationException) {
			 ConstraintViolationException sqlEx = (ConstraintViolationException) cause.getCause();
		     log.error(super.getMessage() + CAUSE + sqlEx.getSQLException().getLocalizedMessage() + " Error code: " + sqlEx.getErrorCode());     
		 }else	if (causeA instanceof ArrayIndexOutOfBoundsException) {
			 ArrayIndexOutOfBoundsException arrayEx = (ArrayIndexOutOfBoundsException) cause.getCause();
		     log.error(super.getMessage() + CAUSE + arrayEx.getLocalizedMessage());     
		 }else {
			 log.error(super.getMessage() + CAUSE + super.getLocalizedMessage());     
		 }
    }
	
	public SoftNutriException(String message) {
		 super(message);
   }
}
