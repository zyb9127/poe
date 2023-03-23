package com.poe.project.poe_project.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

/**
 * JsonUtils
 *x
 * @author Breath
 * @date 2020/7/24
 */
@Slf4j
@Component
public class JsonUtils {

    private final ObjectMapper mapper;

    @Autowired
    public JsonUtils(ObjectMapper mapper) {
        this.mapper = mapper;
        this.mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
        this.mapper.configure(FAIL_ON_EMPTY_BEANS, false);
        this.mapper.setSerializationInclusion(ALWAYS);
    }
    //
    /**
     * 对象转化字符串
     *
     * @param object 对象
     * @return String
     */
    public String toJSONString(Object object) {
        try {
            if (object == null) {
                return null;
            }
            if (object instanceof String) {
                return (String) object;
            }
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            log.debug("Object conversion String failed:{}", ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * 对象转化字符串,返回异常信息
     *
     * @param object 对象
     * @return String
     * @throws JsonProcessingException 异常信息
     */
    public String toJSONStringThrow(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    /**
     * JSON字符串转化对象
     *
     * @param json  JSON字符串
     * @param clazz class
     * @param <T>   泛型
     * @return 返回对象
     */
    public <T> T parseObject(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            log.debug("String conversion Object failed:{}", ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * JSON字符串转化对象
     *
     * @param json  JSON字符串
     * @param clazz class
     * @param <T>   class参数
     * @return class参数
     * @throws IOException 异常信息
     */
    public <T> T parseObjectThrow(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }

    /**
     * Json字符串转为Map对象
     *
     * @param json Json字符串
     * @return Map对象
     */
    public Map<String, Object> parseObject(String json) {
        try {
            return mapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            log.debug("parse json string to object error, {}", ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * Json字符串转为Map对象
     *
     * @param json Json字符串
     * @return Map对象
     * @throws IOException 异常信息
     */
    public Map<String, Object> parseObjectThrow(String json) throws IOException {
        return mapper.readValue(json, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * Json字符串转为指定类型对象
     *
     * @param json         Json字符串
     * @param valueTypeRef 类型
     * @return 对象
     */
    public <T> T parseObject(String json, TypeReference<T> valueTypeRef) {
        try {
            return mapper.readValue(json, valueTypeRef);
        } catch (Exception e) {
            log.debug("parse json string to object error, {}", ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * Json字符串转为指定类型对象
     *
     * @param json         Json字符串
     * @param valueTypeRef 类型
     * @return 对象
     * @throws IOException 异常信息
     */
    public <T> T parseObjectThrow(String json, TypeReference<T> valueTypeRef) throws IOException {
        return mapper.readValue(json, valueTypeRef);
    }

    /**
     * Object对象转为指定类型对象
     *
     * @param object       Object对象
     * @param valueTypeRef 类型
     * @return 对象
     */
    public <T> T parseObject(Object object, TypeReference<T> valueTypeRef) {
        try {
            return mapper.convertValue(object, valueTypeRef);
        } catch (Exception e) {
            log.debug("parse object to object error, {}", ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * Object对象转为指定类型对象
     *
     * @param object       Object对象
     * @param valueTypeRef 类型
     * @return 对象
     * @throws IOException 异常信息
     */
    public <T> T parseObjectThrow(Object object, TypeReference<T> valueTypeRef) throws IOException {
        return mapper.readValue(mapper.writeValueAsString(object), valueTypeRef);
    }

    /**
     * Map转对象
     *
     * @param map   map
     * @param clazz class
     * @param <T>   泛型
     * @return 返回对象
     */
    public <T> T mapToBean(Map map, Class<T> clazz) {
        try {
            return mapper.convertValue(map, clazz);
        } catch (Exception e) {
            log.error("Map conversion Bean failed:{}", ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    public <T> List<T> parseArray(String s, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (StringUtils.isBlank(s) || clazz == null) {
            return result;
        }
        List list = parseObject(s, result.getClass());
        if (CollectionUtils.isEmpty(list)) {
            return result;
        }
        for (Object l : list) {
            if (l == null) {
                continue;
            }
            if (clazz.equals(l.getClass())) {
                result.add((T) l);
            }
            result.add(parseObject(toJSONString(l),clazz));
        }
        return result;
    }
}
