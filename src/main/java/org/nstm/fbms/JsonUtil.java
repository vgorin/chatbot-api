package org.nstm.fbms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author vgorin
 *         file created on 12/7/16 4:48 PM
 */


public class JsonUtil {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	static {
		OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}

	public static String toPrettyJson(Object obj) {
		try {
			return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		}
		catch(JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String toJson(Object obj) {
		try {
			return OBJECT_MAPPER.writeValueAsString(obj);
		}
		catch(JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T fromJson(String json, Class<T> className) {
		try {
			return OBJECT_MAPPER.readValue(json, className);
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
}
