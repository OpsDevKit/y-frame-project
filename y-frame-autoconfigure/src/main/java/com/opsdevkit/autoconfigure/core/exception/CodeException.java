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

import jakarta.annotation.Nullable;
import lombok.Getter;

/**
 * CodeException
 * 带有异常编号的异常基类，继承于RuntimeException
 *
 * @author liyan
 * @since 2025/6/22 11:47
 */
@Getter
public class CodeException extends RuntimeException {

    /**
     * 默认编码
     */
    public static final int DEFAULT_ERROR_CODE = 999999;

    /**
     * 异常编码
     */
    private final Integer code;

    protected CodeException() {
        this(null, null);
    }

    protected CodeException(@Nullable Integer code) {
        this(code, null);
    }

    protected CodeException(@Nullable Integer code, @Nullable String msg) {
        super(msg == null ? "" : msg);
        this.code = code == null ? DEFAULT_ERROR_CODE : code;
    }

    protected CodeException(@Nullable Integer code, @Nullable String msg, Throwable cause) {
        super(msg, cause);
        this.code = code == null ? DEFAULT_ERROR_CODE : code;
    }
}
