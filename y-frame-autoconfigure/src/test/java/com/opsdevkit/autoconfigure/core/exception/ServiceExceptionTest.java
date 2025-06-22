package com.opsdevkit.autoconfigure.core.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServiceExceptionTest {

    private static final Integer TEST_CODE = 1001;
    private static final String TEST_MSG = "Test error message";
    private static final Throwable TEST_CAUSE = new RuntimeException("Test cause");

    @Test
    void testOfWithCode() {
        ServiceException exception = ServiceException.of(TEST_CODE);
        assertEquals(TEST_CODE, exception.getCode());
        assertNotNull(exception.getMessage());
    }

    @Test
    void testOfWithCodeAndMsg() {
        ServiceException exception = ServiceException.of(TEST_CODE, TEST_MSG);
        assertEquals(TEST_CODE, exception.getCode());
        assertEquals(TEST_MSG, exception.getMessage());
    }

    @Test
    void testOfWithCodeAndMsgAndCause() {
        ServiceException exception = ServiceException.of(TEST_CODE, TEST_MSG, TEST_CAUSE);
        assertEquals(TEST_CODE, exception.getCode());
        assertEquals(TEST_MSG, exception.getMessage());
        assertEquals(TEST_CAUSE, exception.getCause());
    }

    @Test
    void testInheritance() {
        ServiceException exception = ServiceException.of(TEST_CODE);
        assertTrue(exception instanceof CodeErrorMsgException);
    }
    
    @Test
    void testNullCode() {
        assertThrows(IllegalArgumentException.class, () -> ServiceException.of(null));
    }
    
    @Test
    void testNullMsg() {
        ServiceException exception = ServiceException.of(TEST_CODE, (String) null);
        assertEquals(TEST_CODE, exception.getCode());
        assertNull(exception.getMessage());
    }
    
    @Test
    void testNullCause() {
        ServiceException exception = ServiceException.of(TEST_CODE, TEST_MSG, null);
        assertEquals(TEST_CODE, exception.getCode());
        assertEquals(TEST_MSG, exception.getMessage());
        assertNull(exception.getCause());
    }
}