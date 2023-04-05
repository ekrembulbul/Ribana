package com.bulbul.ribana.util;

import com.bulbul.ribana.exception.data.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionUtil {

    public static ErrorResponse getErrorResponse(HttpStatus status, String message, String path) {
        return new ErrorResponse()
                .toBuilder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(path)
                .build();
    }

    public static ErrorResponse getInternalServerErrorResponse(String message, String path) {
        return getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, path);
    }

    public static ResponseEntity<Object> getInternalServerErrorResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
