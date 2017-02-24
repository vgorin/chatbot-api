package openchat.api.messenger;

import openchat.api.messenger.json.*;
import openchat.util.JsonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
	private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();
	private static final Logger log = LoggerFactory.getLogger(MessengerClient.class);

	private final String postURL;
	private final String settingsURL;
	private final String userProfileURLTemplate;

	public MessengerClient(String accessToken) {
		this.postURL = String.format("https://graph.facebook.com/v2.6/me/messages?access_token=%s", accessToken);
		this.settingsURL = String.format("https://graph.facebook.com/v2.6/me/thread_settings?access_token=%s", accessToken);
		this.userProfileURLTemplate = String.format("https://graph.facebook.com/v2.6/%%d?fields=first_name,last_name,profile_pic,locale,timezone,gender&access_token=%s", accessToken);
	}

	public static MessengerClient defaultClient(String accessToken) {
		return new MessengerClient(accessToken);
	}

	public UserProfile retrieveUserProfile(long clientId) throws IOException {
		String getURL = String.format(userProfileURLTemplate, clientId);
		HttpGet get = new HttpGet(getURL);
		log.info("HTTP GET {}", getURL);
		HttpResponse httpResponse = HTTP_CLIENT.execute(get);
		HttpEntity entity = httpResponse.getEntity();
		String response = EntityUtils.toString(entity);
		EntityUtils.consume(entity);
		StatusLine line = httpResponse.getStatusLine();
		if(line.getStatusCode() == 200) {
			log.info("{}\n{}", line, response);
			return JsonUtil.parseJson(response, UserProfile.class);
		}
		else {
			log.warn("{}\n{}", line, response);
			return null;
		}
	}

	public Response setGreetingMessage(String text) throws IOException {
		Settings greetingSettings = Settings.createGreeting(text);
		return sendJson(settingsURL, greetingSettings.toJson());
	}

	public Response sendText(long recipientId, String text) throws IOException {
		Message message = new Message(text);
		return send(recipientId, message);
	}

	public Response sendImage(long recipientId, String url) throws IOException {
		Message message = new Message(Attachment.image(url));
		return send(recipientId, message);
	}

	public Response sendReplies(long recipientId, String text, QuickReply... replies) throws IOException {
		return sendReplies(recipientId, text, Arrays.asList(replies));
	}

	public Response sendReplies(long recipientId, String text, List<QuickReply> replies) throws IOException {
		Message message = new Message(text);
		message.quickReplies = replies;
		return send(recipientId, message);
	}

	public Response sendButtons(long recipientId, String text, Button... buttons) throws IOException {
		return sendButtons(recipientId, text, Arrays.asList(buttons));
	}

	public Response sendButtons(long recipientId, String text, List<Button> buttons) throws IOException {
		Message message = new Message();
		message.attachment = new Attachment("template");
		message.attachment.payload = new Payload("button");
		message.attachment.payload.text = text;
		message.attachment.payload.buttons = buttons;
		return send(recipientId, message);
	}

	public Response send(long recipientId, Message message) throws IOException {
		return send(new Recipient(recipientId), message);
	}

	private Response send(Recipient recipient, Message message) throws IOException {
		log.info("sending a message to {}, message = {}", recipient, message);
		String jsonPayload = new Messaging(recipient, message).toJson();
		log.info("json payload for {} is {}", recipient, jsonPayload);
		Response response = sendJson(postURL, jsonPayload);
		if(response != null) {
			log.info("message {} sent back to {} successfully!", message, recipient);
		}
		else {
			log.warn("error replying back to {}", recipient);
		}
		return response;
	}

	private Response sendJson(String postURL, String jsonPayload) throws IOException {
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
			return JsonUtil.parseJson(response, Response.class);
		}
		else {
			log.warn("{}\n{}", line, response);
			return null;
		}
	}

}
