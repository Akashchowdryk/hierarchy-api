package com.example.hierarchy.exception;

public class HierarchyException extends RuntimeException {

    private final String reason;

    public HierarchyException(String message, String reason) {
        super(message);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
