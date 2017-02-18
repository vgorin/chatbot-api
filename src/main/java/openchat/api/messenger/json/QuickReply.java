package openchat.api.messenger.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import openchat.api.messenger.builder.QuickRepliesBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Quick Replies provide a new way to present buttons to the user.
 * Quick Replies appear prominently above the composer, with the keyboard less prominent.
 * When a quick reply is tapped, the message is sent in the conversation with developer-defined metadata in the callback.
 * Also, the buttons are dismissed preventing the issue where users could tap on buttons attached to old messages in a conversation.</p>
 *
 * <p>Quick Replies contain text. Optionally, you can add an image.</p>
 *
 * @see QuickRepliesBuilder
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/send-api-reference/quick-replies">Quick Replies</a>
 *
 * @author vgorin
 *         file created on 11/19/16 2:41 PM
 */


public class QuickReply extends AbstractJson {
	@JsonProperty("content_type")
	public String contentType;
	public String title;
	public String payload;
	@JsonProperty("image_url")
	public String imageUrl;

	public QuickReply() {
	}

	public QuickReply(String contentType) {
		this.contentType = contentType;
	}

	public QuickReply(String contentType, String title) {
		this.contentType = contentType;
		this.title = title;
		this.payload = title;
	}

	public QuickReply(String contentType, String title, String payload) {
		this.contentType = contentType;
		this.title = title;
		this.payload = payload;
	}

	public QuickReply(String contentType, String title, String payload, String imageUrl) {
		this.contentType = contentType;
		this.title = title;
		this.payload = payload;
		this.imageUrl = imageUrl;
	}

	public static QuickReply text(String title) {
		return text(title, title);
	}

	public static QuickReply text(String title, String payload) {
		return text(title, payload, null);
	}

	public static QuickReply text(String title, String payload, String imageUrl) {
		return new QuickReply("text", title, payload, imageUrl);
	}

	public static QuickReply location() {
		return new QuickReply("location");
	}

	public static List<QuickReply> of(String... titles) {
		List<QuickReply> replies = new ArrayList<>(titles.length);
		for(String title: titles) {
			replies.add(text(title));
		}
		return replies;
	}
}
