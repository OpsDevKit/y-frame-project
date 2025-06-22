package com.opsdevkit.autoconfigure.core.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CodeErrorMsgExceptionTest {

    private static final Integer TEST_CODE = 1001;
    private static final String TEST_MSG = "Test error message";
    private static final Throwable TEST_CAUSE = new RuntimeException("Test cause");

    @BeforeEach
    void setUp() {
        // 设置默认ErrorMsg实现
        CodeErrorMsgException.setErrorMsg(code -> TEST_MSG);
    }

    @Test
    void testConstructorWithCode() {
        CodeErrorMsgException exception = new CodeErrorMsgException(TEST_CODE);
        assertEquals(TEST_CODE, exception.getCode());
        assertEquals(TEST_MSG, exception.getMessage());
    }

    @Test
    void testConstructorWithCodeAndMsg() {
        CodeErrorMsgException exception = new CodeErrorMsgException(TEST_CODE, "Custom message");
        assertEquals(TEST_CODE, exception.getCode());
        assertEquals("Custom message", exception.getMessage());
    }

    @Test
    void testConstructorWithCodeAndMsgAndCause() {
        CodeErrorMsgException exception = new CodeErrorMsgException(TEST_CODE, "Custom message", TEST_CAUSE);
        assertEquals(TEST_CODE, exception.getCode());
        assertEquals("Custom message", exception.getMessage());
        assertEquals(TEST_CAUSE, exception.getCause());
    }

    @Test
    void testErrorMsgInterface() {
        // 测试自定义ErrorMsg实现
        String customMsg = "Custom message";
        CodeErrorMsgException.setErrorMsg(code -> customMsg);
        
        CodeErrorMsgException exception = new CodeErrorMsgException(TEST_CODE);
        assertEquals(customMsg, exception.getMessage());
    }

    @Test
    void testDefaultErrorMsg() {
        // 重置为默认实现
        CodeErrorMsgException.setErrorMsg(code -> "-");
        CodeErrorMsgException exception = new CodeErrorMsgException(TEST_CODE);
        assertEquals("-", exception.getMessage());
    }

    @Test
    void testInheritance() {
        CodeErrorMsgException exception = new CodeErrorMsgException(TEST_CODE);
        assertTrue(exception instanceof CodeException);
    }
}