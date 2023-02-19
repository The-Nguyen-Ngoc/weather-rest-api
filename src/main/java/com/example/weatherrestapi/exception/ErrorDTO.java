package com.example.weatherrestapi.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDTO {
    private Date timestamp;
    private int status;
    private String path;
    private String error;

}
