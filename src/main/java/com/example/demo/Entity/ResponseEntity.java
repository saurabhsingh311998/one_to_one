package com.example.demo.Entity;

import lombok.Data;

@Data
public class ResponseEntity <responseData> {
    private boolean error;
    private int status;
    private responseData data;
    private String message;

    public ResponseEntity(boolean errorFlag, int responseStatus, responseData responseData,String msg) {
        error = errorFlag;
        status = responseStatus;
        data = responseData;
        message = msg;
    }
}
