package italo.siserp.infra.entrypoint.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import italo.siserp.core.exception.ErrorException;
import italo.siserp.infra.entrypoint.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
  
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<Object> throwErrorException( ErrorException e ) {
        return ResponseEntity.badRequest().body( new ErrorResponse( e.getMessage() ) );
    }

}
