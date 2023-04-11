package com.bulbul.ribana.exception.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class ErrorResponse {

    private Timestamp timestamp;

    private Integer status;

    private String error;

    private String trace;

    private String message;

    private String path;

    public ErrorResponse() {
        timestamp = new Timestamp(System.currentTimeMillis());
    }

}
