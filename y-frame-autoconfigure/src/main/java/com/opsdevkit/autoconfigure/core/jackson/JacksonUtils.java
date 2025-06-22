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

package com.opsdevkit.autoconfigure.core.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.opsdevkit.autoconfigure.core.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * JacksonUtils
 * 该工具类提供了一系列使用Jackson库进行JSON数据序列化和反序列化的静态方法。
 *
 * @author liyan
 * @since 2025/6/22 13:19
 */
public class JacksonUtils {

    /**
     * 私有构造函数，防止外部实例化该工具类
     */
    private JacksonUtils() {
    }

    /**
     * 定义JSON解析错误的错误码
     */
    private static final Integer JSON_PARSE_ERROR = 100001;

    /**
     * 静态的ObjectMapper实例，用于处理JSON数据的序列化和反序列化
     */
    private static ObjectMapper objectMapper;

    /**
     * 设置ObjectMapper实例
     *
     * @param objectMapper 要设置的ObjectMapper实例
     */
    public static void setObjectMapper(ObjectMapper objectMapper) {
        JacksonUtils.objectMapper = objectMapper;
    }

    /**
     * 获取ObjectMapper实例，如果实例为空则进行初始化
     *
     * @return ObjectMapper实例
     */
    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            // 设置序列化时忽略值为null的属性
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            // 禁用在遇到未知属性时抛出异常的特性
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }
        return objectMapper;
    }

    /**
     * 获取TypeFactory实例
     *
     * @return TypeFactory实例
     */
    public static TypeFactory getTypeFactory() {
        return getObjectMapper().getTypeFactory();
    }

    /**
     * 将输入对象转换为字符串，如果对象本身是字符串则直接返回，否则将其转换为JSON字符串
     *
     * @param content 输入对象
     * @return 字符串形式的结果
     */
    private static String valueString(Object content) {
        return content instanceof String st ? st : toJson(content);
    }

    /**
     * 将JSON数据反序列化为指定类型的对象
     *
     * @param content 输入的JSON数据，可以是字符串或其他对象
     * @param clazz   目标对象的类类型
     * @param <T>     泛型类型
     * @return 反序列化后的对象
     */
    public static <T> T fromJson(Object content, Class<T> clazz) {
        try {
            return getObjectMapper().readValue(valueString(content), clazz);
        } catch (JsonProcessingException e) {
            throw newInstance(e);
        }
    }

    /**
     * 将JSON数据反序列化为指定类型引用的对象
     *
     * @param content   输入的JSON数据，可以是字符串或其他对象
     * @param reference 目标对象的类型引用
     * @param <T>       泛型类型
     * @return 反序列化后的对象
     */
    public static <T> T fromJson(Object content, TypeReference<T> reference) {
        try {
            return getObjectMapper().readValue(valueString(content), reference);
        } catch (Exception e) {
            throw newInstance(e);
        }
    }

    /**
     * 将JSON数据反序列化为指定JavaType的对象
     *
     * @param content    输入的JSON数据，可以是字符串或其他对象
     * @param javaType   目标对象的JavaType
     * @param <T>        泛型类型
     * @return 反序列化后的对象
     */
    public static <T> T fromJson(Object content, JavaType javaType) {
        try {
            return getObjectMapper().readValue(valueString(content), javaType);
        } catch (Exception e) {
            throw newInstance(e);
        }
    }

    /**
     * 将JSON数据反序列化为指定类型的列表
     *
     * @param content 输入的JSON数据，可以是字符串或其他对象
     * @param clazz   列表元素的类类型
     * @param <T>     泛型类型
     * @return 反序列化后的列表
     */
    public static <T> List<T> fromList(Object content, Class<T> clazz) {
        return fromJson(valueString(content), getTypeFactory().constructCollectionType(List.class, clazz));
    }

/**
 * 将输入的JSON数据反序列化为包含Map<String, Object>的列表。
 * 输入的JSON数据可以是字符串或其他对象，如果是其他对象会先将其转换为JSON字符串。
 *
 * @param content 输入的JSON数据，可以是字符串或其他对象
 * @return 反序列化后的包含Map<String, Object>的列表
 */
public static List<Map<String, Object>> fromListMap(Object content) {
    return fromJson(valueString(content), getTypeFactory().constructCollectionType(List.class, Map.class));
}

    /**
     * 将JSON数据反序列化为包含指定键值类型的Map的列表
     *
     * @param content    输入的JSON数据，可以是字符串或其他对象
     * @param keyClass   键的类类型
     * @param valueClass 值的类类型
     * @param <K>        键的泛型类型
     * @param <V>        值的泛型类型
     * @return 反序列化后的列表
     */
    public static <K, V> List<Map<K, V>> fromListMap(Object content, Class<K> keyClass, Class<V> valueClass) {
        return fromJson(valueString(content), getTypeFactory().constructCollectionType(List.class, getTypeFactory().constructMapType(Map.class, keyClass, valueClass)));
    }

    /**
     * 将JSON数据反序列化为Map<String, Object>
     *
     * @param content 输入的JSON数据，可以是字符串或其他对象
     * @return 反序列化后的Map
     */
    public static Map<String, Object> fromMap(Object content) {
        return fromJson(valueString(content), new TypeReference<>() {
        });
    }

    /**
     * 将JSON数据反序列化为指定键值类型的Map
     *
     * @param content    输入的JSON数据，可以是字符串或其他对象
     * @param keyClass   键的类类型
     * @param valueClass 值的类类型
     * @param <K>        键的泛型类型
     * @param <V>        值的泛型类型
     * @return 反序列化后的Map
     */
    public static <K, V> Map<K, V> fromMap(Object content, Class<K> keyClass, Class<V> valueClass) {
        return fromJson(valueString(content), getTypeFactory().constructMapType(Map.class, keyClass, valueClass));
    }

    /**
     * 将对象序列化为JSON字符串
     *
     * @param value 要序列化的对象
     * @return 序列化后的JSON字符串
     */
    public static String toJson(Object value) {
        try {
            return getObjectMapper().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw newInstance(e);
        }
    }

    /**
     * 创建一个新的服务异常实例
     *
     * @param cause 异常原因
     * @return 服务异常实例
     */
    private static ServiceException newInstance(Throwable cause) {
        return ServiceException.of(JSON_PARSE_ERROR, cause);
    }

}
