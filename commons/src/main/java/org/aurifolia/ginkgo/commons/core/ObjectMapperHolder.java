package org.aurifolia.ginkgo.commons.core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

/**
 * 获取ObjectMapper单例
 *
 * @author VNElinpe
 * @since 1.0
 **/
public class ObjectMapperHolder {
    @Getter
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        configure(objectMapper);
    }

    /**
     * 配置ObjectMapper
     *
     * @param objectMapper objectMapper
     */
    public static void configure(ObjectMapper objectMapper) {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}
