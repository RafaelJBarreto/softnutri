package br.com.softnutri.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.softnutri.exception.TokenRefreshException;


@RestControllerAdvice
public class TokenControllerAdvice {

	@ExceptionHandler(value = TokenRefreshException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorMessage handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.FORBIDDEN.value(), LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
	}

}
