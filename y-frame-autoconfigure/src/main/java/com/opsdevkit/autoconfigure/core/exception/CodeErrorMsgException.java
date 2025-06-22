/*
 Copyright 2025-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.opsdevkit.autoconfigure.core.exception;

import jakarta.annotation.Nonnull;

/**
 * CodeErrorMsgException
 * 带有异常code与msg映射关系的{@link CodeException}
 *
 * @author liyan
 * @since 2025/6/22 13:03
 */
// 定义一个继承自 CodeException 的异常类 CodeErrorMsgException
public class CodeErrorMsgException extends CodeException {

    // 静态变量，用于存储错误消息映射对象
    private static ErrorMsg errorMsg = code -> "-";

    /**
     * 设置错误消息映射对象
     *
     * @param errorMsg 错误消息映射对象
     */
    public static void setErrorMsg(ErrorMsg errorMsg) {
        CodeErrorMsgException.errorMsg = errorMsg;
    }

    /**
     * 受保护的构造函数，用于创建 CodeErrorMsgException 实例
     *
     * @param code 异常代码，不能为空
     */
    protected CodeErrorMsgException(@Nonnull Integer code) {
        // 调用父类的构造函数，传入异常代码和对应的错误消息
        super(code, errorMsg.getErrorMsg(code));
    }

    /**
     * 定义一个静态接口 ErrorMsg，用于获取异常代码对应的错误消息
     */
    public static interface ErrorMsg {
        /**
         * 根据异常代码获取对应的错误消息
         *
         * @param code 异常代码
         * @return 错误消息
         */
        String getErrorMsg(Integer code);
    }
}
