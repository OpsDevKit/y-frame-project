package com.opsdevkit.autoconfigure.core.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CodeExceptionTest {

    @Test
    void testDefaultConstructor() {
        CodeException exception = new CodeException();
        assertEquals(CodeException.DEFAULT_ERROR_CODE, exception.getCode());
        assertEquals("", exception.getMessage());
    }

    @Test
    void testConstructorWithCode() {
        CodeException exception = new CodeException(123);
        assertEquals(123, exception.getCode());
        assertEquals("", exception.getMessage());
    }

    @Test
    void testConstructorWithCodeAndMessage() {
        CodeException exception = new CodeException(456, "Test message");
        assertEquals(456, exception.getCode());
        assertEquals("Test message", exception.getMessage());
    }

    @Test
    void testConstructorWithCodeAndMessageAndCause() {
        Throwable cause = new RuntimeException("Root cause");
        CodeException exception = new CodeException(789, "Test message", cause);
        assertEquals(789, exception.getCode());
        assertEquals("Test message", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testNullCodeUsesDefault() {
        CodeException exception = new CodeException(null, "Test");
        assertEquals(CodeException.DEFAULT_ERROR_CODE, exception.getCode());
    }

    @Test
    void testNullMessageUsesEmptyString() {
        CodeException exception = new CodeException(123, null);
        assertEquals("", exception.getMessage());
    }
}