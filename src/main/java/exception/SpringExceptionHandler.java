package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class SpringExceptionHandler {

    @ExceptionHandler(InvalidItemException.class)
    public ResponseEntity<ErrorMessage> handleInvalidItemException(InvalidItemException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage( ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorMessage> handleInvalidUserException(InvalidUserException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage( ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
