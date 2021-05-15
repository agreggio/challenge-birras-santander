package com.agreggio.challenge.birras.santander.common.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper converter = new ObjectMapper();

    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Convert object to JSON.</br>
     * Convert objects (dto, entities, etc.) to JSON strings, if there are errors,
     * this method returns a null value.</br>
     * <strong>note:</strong>if ibject is null, this method return null value.
     *
     * @param object
     * @return json {@link String} result of convert object in JSON string.
     */
    public static String toJson(Object object) {

        LOGGER.info("Convert object to json");

        if(object == null) {
            return null;
        }

        try {

            String json = converter.writeValueAsString(object);

            LOGGER.debug("json: {}", json);

            return json;

        } catch (JsonProcessingException e) {

            LOGGER.error("Error to convert object to json: {}", e.getMessage());

        }
        return null;

    }
}
