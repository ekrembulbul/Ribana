package com.bulbul.ribana.exception.handler;

import com.bulbul.ribana.configuration.MessageProperties;
import com.bulbul.ribana.exception.custom.ResultsAndFieldsNotEqualException;
import com.bulbul.ribana.exception.custom.WrongPropertiesAndDirectionsException;
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

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ResultsAndFieldsNotEqualException.class)
    public ResponseEntity<Object> handleResultsAndFieldsNotEqualException(ResultsAndFieldsNotEqualException exception, HttpServletRequest request) {
        exception.printStackTrace();

        ErrorResponse errorResponse = getInternalServerErrorResponse(messageProperties.resultLengthAndFieldLengthMustBeEqual,
                                                                     request.getRequestURI());
        return getInternalServerErrorResponseEntity(errorResponse);
    }

    @ExceptionHandler(value = WrongPropertiesAndDirectionsException.class)
    public ResponseEntity<Object> handleWrongPropertiesAndDirectionsException(WrongPropertiesAndDirectionsException exception, HttpServletRequest request) {
        exception.printStackTrace();

        ErrorResponse errorResponse = getInternalServerErrorResponse(messageProperties.propertyAndDirectionParametersAreWrong,
                                                                     request.getRequestURI());
        return getInternalServerErrorResponseEntity(errorResponse);
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

    private ErrorResponse getInternalServerErrorResponse(String message, String path) {
        return getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, path);
    }

    private ResponseEntity<Object> getInternalServerErrorResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
