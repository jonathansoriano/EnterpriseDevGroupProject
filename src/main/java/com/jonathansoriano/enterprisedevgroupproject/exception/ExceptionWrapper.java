package com.jonathansoriano.enterprisedevgroupproject.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ExceptionWrapper
{
    private String timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    //PURPOSE OF THIS CLASS: This class:
    //1. Makes your error responses consistent
    //2. Gives clients useful info like timestamp, status, and path of URL
    //3. Makes debugging and client integration easier
    public ExceptionWrapper(Integer status, String message, String path)
    {
        this.timeStamp = LocalDateTime.now().toString();
        this.status = status;
        this.error = HttpStatus.valueOf(status).getReasonPhrase();
        this.message = message;
        this.path = path;
    }


}
