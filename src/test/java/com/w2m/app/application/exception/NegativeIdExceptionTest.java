package com.w2m.app.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NegativeIdExceptionTest {

    @Test
    void testNegativeIdExceptionConstructor() {
        String message = "El ID proporcionado no puede ser negativo";
        NegativeIdException exception = new NegativeIdException(message);

        assertEquals(message, exception.getMessage());
    }
}