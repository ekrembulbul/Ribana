package com.bulbul.ribana.exception.handler;

import com.bulbul.ribana.configuration.message.ErrorMessageProperties;
import com.bulbul.ribana.exception.custom.ResultsAndFieldsNotEqualException;
import com.bulbul.ribana.exception.custom.WrongPropertiesAndDirectionsException;
import com.bulbul.ribana.exception.data.ErrorResponse;
import com.bulbul.ribana.util.ExceptionUtil;
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
    ErrorMessageProperties messageProperties;

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception exception, HttpServletRequest request) {
        exception.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ResultsAndFieldsNotEqualException.class)
    public ResponseEntity<Object> handleResultsAndFieldsNotEqualException(ResultsAndFieldsNotEqualException exception, HttpServletRequest request) {
        exception.printStackTrace();

        ErrorResponse errorResponse = ExceptionUtil.getInternalServerErrorResponse(messageProperties.resultLengthAndFieldLengthMustBeEqual,
                                                                                   request.getRequestURI());
        return ExceptionUtil.getInternalServerErrorResponseEntity(errorResponse);
    }

    @ExceptionHandler(value = WrongPropertiesAndDirectionsException.class)
    public ResponseEntity<Object> handleWrongPropertiesAndDirectionsException(WrongPropertiesAndDirectionsException exception, HttpServletRequest request) {
        exception.printStackTrace();

        ErrorResponse errorResponse = ExceptionUtil.getInternalServerErrorResponse(messageProperties.propertyAndDirectionParametersAreWrong,
                                                                     request.getRequestURI());
        return ExceptionUtil.getInternalServerErrorResponseEntity(errorResponse);
    }

}
