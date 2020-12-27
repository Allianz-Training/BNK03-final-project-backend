package com.bnk03.bnklaim.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJson {
    private static ObjectMapper mapper = new ObjectMapper();

    private ObjectToJson() {
    }

    public static String toJsonString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return e.toString();
        }
    }
}
