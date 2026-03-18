package ca.mcgill.ecse321.eventregistration.exception;

import ca.mcgill.ecse321.eventregistration.dto.ErrorDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class EventRegistrationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>();
        for (var error : e.getConstraintViolations()) {
            errors.add(error.getMessage());
        }
        return new ResponseEntity<>(new ErrorDto(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventRegistrationException.class)
    public ResponseEntity<ErrorDto> handleEventRegistrationException(EventRegistrationException e) {
        return new ResponseEntity<>(new ErrorDto(e.getMessage()), e.getStatus());
    }
}