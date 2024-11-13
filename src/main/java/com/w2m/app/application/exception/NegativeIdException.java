package com.w2m.app.application.exception;

/**
 * Custom exception thrown when an invalid negative ID is encountered.
 * This exception extends {@link RuntimeException} and provides a constructor
 * that allows passing a custom message.
 */
public class NegativeIdException extends RuntimeException{

    /**
     * Constructs a new {@code NegativeIdException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the
     *                {@link Throwable#getMessage()} method)
     */
    public NegativeIdException(String message) {
        super(message);
    }
}
