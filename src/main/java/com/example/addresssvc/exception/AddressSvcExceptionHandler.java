package com.example.addresssvc.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class AddressSvcExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeException(
            RuntimeException ex, WebRequest request) {

        log.error(" RuntimeException", ex);
        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.setErrorMessage(ex.getMessage());
        errorMessage.setErrorCode("5000");


        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex,
             HttpHeaders headers, HttpStatus status, WebRequest request)     {
        log.error(" MethodArgumentNotValidException", ex);
        ErrorMessage errorMessage = new ErrorMessage();
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        errorMessage.setErrorMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        errorMessage.setErrorCode("1000");
        errorMessage.setDetails(details);

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
