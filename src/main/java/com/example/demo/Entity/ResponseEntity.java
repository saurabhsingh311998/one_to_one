package com.example.demo.Entity;

import lombok.Data;

@Data
public class ResponseEntity <responseData> {
    private boolean error;
    private int status;
    private responseData data;

    public ResponseEntity(boolean errorFlag, int responseStatus, responseData responseData) {
        error = errorFlag;
        status = responseStatus;
        data = responseData;
    }
}
