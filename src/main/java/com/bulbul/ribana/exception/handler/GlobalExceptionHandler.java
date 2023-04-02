package com.bulbul.ribana.exception.handler;

import com.bulbul.ribana.configuration.MessageProperties;
import com.bulbul.ribana.exception.custom.ResultsAndFieldsNotEqualException;
import com.bulbul.ribana.exception.data.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    MessageProperties messageProperties;

    private final HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse(), internalServerError);
    }

    @ExceptionHandler(value = ResultsAndFieldsNotEqualException.class)
    public ResponseEntity<Object> handleResultsAndFieldsNotEqualException(ResultsAndFieldsNotEqualException exception, HttpServletRequest request) {
        exception.printStackTrace();

        HttpStatus status = internalServerError;
        ErrorResponse errorResponse = getErrorResponse(status,
                                                       messageProperties.resultAndFieldNotEqualMessage,
                                                       request.getRequestURI());
        return new ResponseEntity<>(errorResponse, status);
    }

    private ErrorResponse getErrorResponse(HttpStatus status, String message, String path) {
        return new ErrorResponse()
                .toBuilder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(path)
                .build();
    }

}
