package openchat.api.messenger;

import openchat.api.messenger.json.*;
import openchat.util.JsonUtil;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
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
	private final String profileURL;
	private final String userProfileURLTemplate;

	public MessengerClient(String accessToken) {
		this(2.12, accessToken);
	}

	public MessengerClient(double version, String accessToken) {
		this("https://graph.facebook.com", version, accessToken);
	}

	public MessengerClient(String baseURL, double version, String accessToken) {
		this(
				String.format("%s/v%s/me/messages?access_token=%s", baseURL, version, accessToken),
				String.format("%s/v%s/me/thread_settings?access_token=%s", baseURL, version, accessToken),
				String.format("%s/v%s/me/messenger_profile?access_token=%s", baseURL, version, accessToken),
				String.format("%s/v%s/%%d?fields=first_name,last_name,profile_pic,locale,timezone,gender&access_token=%s", baseURL, version, accessToken)
		);
	}

	public MessengerClient(String postURL, String settingsURL, String profileURL, String userProfileURLTemplate) {
		this.postURL = postURL;
		this.settingsURL = settingsURL;
		this.profileURL = profileURL;
		this.userProfileURLTemplate = userProfileURLTemplate;
	}

	public Response setMenu(PersistedMenu menu) throws IOException {
		return sendJson(profileURL, menu.toJson());
	}

	public PersistedMenu getMenu() throws IOException {
		String getURL = String.format("%s&fields=persistent_menu", profileURL);
		String json = getJson(getURL);
		return json == null? null: JsonUtil.parseJson(json, PersistedMenu.class);
	}

	public int deleteMenu() throws IOException {
		HttpDelete delete = new HttpDelete(profileURL);
		String jsonPayload = "{\n\t\"fields\":[\n\t\t\"persistent_menu\"\n\t]\n}";
		log.info("HTTP DELETE {}\n{}", profileURL, jsonPayload);
		return HTTP_CLIENT.execute(delete, HttpResponse::getCode);
	}

	public UserProfile retrieveUserProfile(long clientId) throws IOException {
		String getURL = String.format(userProfileURLTemplate, clientId);
		String json = getJson(getURL);
		return json == null? null: JsonUtil.parseJson(json, UserProfile.class);
	}

	public Response setGreetingMessage(String text) throws IOException {
		Settings settings = Settings.createGreeting(text);
		return sendJson(settingsURL, settings.toJson());
	}

	public Response setGetStartedButton() throws IOException {
		return setGetStartedButton("/start");
	}

	public Response setGetStartedButton(String payload) throws IOException {
		Settings settings = Settings.createGetStartedButton(payload);
		return sendJson(settingsURL, settings.toJson());
	}

	public Response askLocation(long recipientId, String text) throws IOException {
		return sendReplies(recipientId, text, QuickReply.location());
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
		return HTTP_CLIENT.execute(post, response -> {
			int responseCode = response.getCode();
			String responseBody = EntityUtils.toString(response.getEntity());
			if(responseCode != 200) {
				log.warn("{}\n{}", responseCode, responseBody);
				return null;
			}
			log.info("{}\n{}", responseCode, responseBody);
			return JsonUtil.parseJson(responseBody, Response.class);
		});
	}

	private String getJson(String getURL) throws IOException {
		HttpGet get = new HttpGet(getURL);
		log.info("HTTP GET {}", getURL);
		return HTTP_CLIENT.execute(get, response -> {
			int responseCode = response.getCode();
			String responseBody = EntityUtils.toString(response.getEntity());
			if(responseCode != 200) {
				log.warn("{}\n{}", responseCode, responseBody);
				return null;
			}
			log.info("{}\n{}", responseCode, responseBody);
			return responseBody;
		});
	}

}
