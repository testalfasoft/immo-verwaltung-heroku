package pl.alfasoft.immo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ImmoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BadRequestAlertException.class)
    public ResponseEntity<Object> handleEventNotExistException(WebRequest webRequest, BadRequestAlertException e) {

        return handleExceptionInternal(e, e.getMessage(), null, HttpStatus.NOT_FOUND, webRequest);
    }
}
