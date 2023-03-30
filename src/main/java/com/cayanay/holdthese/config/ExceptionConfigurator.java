package com.cayanay.holdthese.config;

import com.cayanay.holdthese.core.utilities.exceptions.BusinessException;
import com.cayanay.holdthese.core.utilities.exceptions.NotFoundException;
import com.cayanay.holdthese.core.utilities.exceptions.ProblemDetails;
import com.cayanay.holdthese.core.utilities.exceptions.ValidationProblemDetails;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
@Component
public class ExceptionConfigurator {
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ProblemDetails handleBusinessException(BusinessException businessException) {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setMessage(businessException.getMessage());
        return problemDetails;
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ProblemDetails handleNotFoundException(NotFoundException notFoundException) {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setMessage(notFoundException.getMessage());
        return problemDetails;
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ProblemDetails handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setMessage("Validation exception! Fields does not satisfy needs.");
        validationProblemDetails.setValidationErrors(new HashMap<>());

        for (FieldError fieldError : methodArgumentNotValidException.getFieldErrors()) {
            validationProblemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return validationProblemDetails;
    }
}

