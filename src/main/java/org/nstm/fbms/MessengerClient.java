package org.nstm.fbms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.nstm.fbms.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author vgorin
 *         file created on 11/19/16 3:48 PM
 */

public class MessengerClient {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();
	private static final Logger log = LoggerFactory.getLogger(MessengerClient.class);

	private final String postURL;

	static {
		OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
	}

	/**
	 *
	 * @param postURL https://graph.facebook.com/v2.6/me/messages?access_token=&lt;token&gt;
	 */
	public MessengerClient(String postURL) {
		this.postURL = postURL;
	}

	public Response send(String recipientId, String text) throws IOException {
		Message message = new Message(text);
		return send(new Recipient(recipientId), message);
	}

	public Response send(String recipientId, String text, QuickReply... replies) throws IOException {
		return send(recipientId, text, Arrays.asList(replies));
	}

	public Response send(String recipientId, String text, List<QuickReply> replies) throws IOException {
		Recipient recipient = new Recipient(recipientId);
		Message message = new Message(text);
		message.quickReplies = replies;
		return send(recipient, message);
	}

	private Response send(Recipient recipient, Message message) throws IOException {
		log.info("sending a message to {}, message = {}", recipient, message);
		String jsonPayload = OBJECT_MAPPER.writeValueAsString(new Messaging(recipient, message));
		log.info("json payload for {} is {}", recipient, jsonPayload);
		Response response = sendJson(jsonPayload);
		if(response != null) {
			log.info("message {} sent back to {} successfully!", message, recipient);
		}
		else {
			log.warn("error replying back to {}", recipient);
		}
		return response;
	}

	private Response sendJson(String jsonPayload) throws IOException {
		HttpPost post = new HttpPost(postURL);
		post.setEntity(new StringEntity(jsonPayload, ContentType.APPLICATION_JSON));

		log.info("HTTP POST {}\n{}", postURL, jsonPayload);
		HttpResponse httpResponse = HTTP_CLIENT.execute(post);
		HttpEntity entity = httpResponse.getEntity();
		String response = EntityUtils.toString(entity);
		EntityUtils.consume(entity);
		StatusLine line = httpResponse.getStatusLine();
		if(line.getStatusCode() == 200) {
			log.info("{}\n{}", line, response);
			return OBJECT_MAPPER.readValue(response, Response.class);
		}
		else {
			log.warn("{}\n{}", line, response);
			return null;
		}
	}

}
