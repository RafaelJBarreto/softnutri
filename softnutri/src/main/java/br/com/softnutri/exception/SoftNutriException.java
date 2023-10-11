package br.com.softnutri.exception;

import org.hibernate.exception.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("serial")
@Slf4j
public class SoftNutriException extends Exception {
	
	public SoftNutriException(String message, Throwable cause) {
		 Throwable causeA = cause.getCause();
		 if (causeA instanceof ConstraintViolationException) {
			 ConstraintViolationException sqlEx = (ConstraintViolationException) cause.getCause();
		     log.error(message + "Cause: " + sqlEx.getSQLException().getLocalizedMessage() + " Error code: " + sqlEx.getErrorCode());     
		 }
    }
}
