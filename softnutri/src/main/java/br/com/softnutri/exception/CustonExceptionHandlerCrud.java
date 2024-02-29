package br.com.softnutri.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.softnutri.config.security.payload.response.MessageResponse;

@ControllerAdvice
public class CustonExceptionHandlerCrud {
	
	@ExceptionHandler({SoftNutriException.class})
    public ResponseEntity<MessageResponse> handleRuntimeException(SoftNutriException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(exception.getMessage()));
    }
}
