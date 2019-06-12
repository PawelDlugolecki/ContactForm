package com.app.exceptions;

public enum ExceptionCode {

    SERVICE("SERVICE EXCEPTION"),
    VALIDATION("VALIDATION EXCEPTION");

    private String description;

    ExceptionCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
