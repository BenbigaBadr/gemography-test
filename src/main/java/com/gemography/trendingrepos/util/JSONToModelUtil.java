package com.gemography.trendingrepos.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class JSONToModelUtil {

    private JSONToModelUtil()
    {

    }

    public static <T> T convertToObject(String json, Class<T> valueType)
    {
        T elementMapped = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            elementMapped = mapper.readValue(json,valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elementMapped;
    }
}
