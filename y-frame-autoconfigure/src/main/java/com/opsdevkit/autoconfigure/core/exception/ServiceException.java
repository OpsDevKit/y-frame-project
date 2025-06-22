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

/**
 * ServiceException
 *
 * @author liyan
 * @since 2025/6/22 13:20
 */
public class ServiceException extends CodeErrorMsgException {
    protected ServiceException(Integer code) {
        super(code);
    }

    protected ServiceException(Integer code, String msg) {
        super(code, msg);
    }

    protected ServiceException(Integer code, Throwable cause) {
        super(code, cause);
    }

    protected ServiceException(Integer code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    /**
     * 创建仅包含错误码的 ServiceException 实例
     *
     * @param code 错误码
     * @return ServiceException 实例
     */
    public static ServiceException of(Integer code) {
        return new ServiceException(code);
    }

    /**
     * 创建包含错误码和引发该异常的原因的 ServiceException 实例
     *
     * @param code 错误码
     * @param cause  引发该异常的原因
     * @return ServiceException 实例
     */
    public static ServiceException of(Integer code, Throwable cause) {
        return new ServiceException(code, cause);
    }

    /**
     * 创建包含错误码和错误信息的 ServiceException 实例
     *
     * @param code 错误码
     * @param msg  错误信息
     * @return ServiceException 实例
     */
    public static ServiceException of(Integer code, String msg) {
        return new ServiceException(code, msg);
    }

    /**
     * 创建包含错误码、错误信息和异常原因的 ServiceException 实例
     *
     * @param code  错误码
     * @param msg   错误信息
     * @param cause 异常原因
     * @return ServiceException 实例
     */
    public static ServiceException of(Integer code, String msg, Throwable cause) {
        return new ServiceException(code, msg, cause);
    }
}

