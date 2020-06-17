package openchat.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import java.io.IOException;

/**
 * @author vgorin
 *         file created on 12/7/16 4:48 PM
 */


public class JsonUtil {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	static {
		// exclude null and empty values from output XML/JSON
		OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		// allow reading of JSON values without quotes
		OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		// do not fail on unknown JSON properties
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// support both Jackson and JAXB annotations
		OBJECT_MAPPER.setAnnotationIntrospector(new AnnotationIntrospectorPair(new JacksonAnnotationIntrospector(), new JaxbAnnotationIntrospector(TypeFactory.defaultInstance())));
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

	public static <T> T parseJson(String json, Class<T> className) {
		try {
			return OBJECT_MAPPER.readValue(json, className);
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
}
