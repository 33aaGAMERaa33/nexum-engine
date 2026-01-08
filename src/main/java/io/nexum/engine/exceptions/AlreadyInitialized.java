package io.nexum.engine.exceptions;

public class AlreadyInitialized extends RuntimeException {
    public AlreadyInitialized(String message) {
        super(message);
    }
}
