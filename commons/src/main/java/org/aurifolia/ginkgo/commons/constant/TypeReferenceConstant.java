package org.aurifolia.ginkgo.commons.constant;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Map;

/**
 * ObjectMapper反序列化使用到的一些泛型
 *
 * @author VNElinpe
 * @since 1.0
 **/
public class TypeReferenceConstant {
    /**
     * Map<String, Object>
     */
    public static TypeReference<Map<String, Object>> MAP_STRING_OBJECT = new TypeReference<>() { };
}
