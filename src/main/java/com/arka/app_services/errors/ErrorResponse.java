package com.arka.app_services.errors;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private ZonedDateTime timestamp;

    private Integer statusCode;

    private List<String> message;

    private String stackTrace;

    public static ErrorResponse createInstance() {
        return new ErrorResponse().setTimestamp(ZonedDateTime.now());
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public ErrorResponse setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public ErrorResponse setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public List<String> getMessage() {
        return message;
    }

    public ErrorResponse setMessage(List<String> message) {
        this.message = message;
        return this;
    }

    public ErrorResponse addMessage(String message) {
        if (this.message == null) {
            this.message = new ArrayList<>();
        }
        this.message.add(message);
        return this;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public ErrorResponse setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
        return this;
    }

}
