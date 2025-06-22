package com.opsdevkit.autoconfigure.core.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CodeErrorMsgExceptionTest {

    private static final Integer TEST_CODE = 1001;
    private static final String TEST_MSG = "Test error message";

    @BeforeEach
    void setUp() {
        // 设置ErrorMsg实现
        CodeErrorMsgException.setErrorMsg(code -> TEST_MSG);
    }

    @Test
    void testConstructorWithCode() {
        CodeErrorMsgException exception = new CodeErrorMsgException(TEST_CODE);
        assertEquals(TEST_CODE, exception.getCode());
        assertEquals(TEST_MSG, exception.getMessage());
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
    void testInheritance() {
        CodeErrorMsgException exception = new CodeErrorMsgException(TEST_CODE);
        assertTrue(exception instanceof CodeException);
    }
}