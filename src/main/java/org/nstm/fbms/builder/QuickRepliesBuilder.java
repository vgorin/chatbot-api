package org.nstm.fbms.builder;

import org.nstm.fbms.json.QuickReply;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>Quick Replies provide a new way to present buttons to the user.
 * Quick Replies appear prominently above the composer, with the keyboard less prominent.
 * When a quick reply is tapped, the message is sent in the conversation with developer-defined metadata in the callback.
 * Also, the buttons are dismissed preventing the issue where users could tap on buttons attached to old messages in a conversation.</p>
 *
 * <p>Quick Replies contain text. Optionally, you can add an image.</p>
 *
 * @see org.nstm.fbms.json.QuickReply
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/send-api-reference/quick-replies">Quick Replies</a>
 *
 * @author vgorin
 *         file created on 11/19/16 6:29 PM
 */


public class QuickRepliesBuilder extends GenericBuilder<List<QuickReply>> {
	private List<QuickReply> replies;

	public QuickRepliesBuilder(int capacity) {
		replies = new ArrayList<>(capacity);
	}

	public QuickRepliesBuilder() {
		replies = new LinkedList<>();
	}

	public static QuickRepliesBuilder builder(int capacity) {
		return new QuickRepliesBuilder(capacity);
	}

	public static QuickRepliesBuilder builder() {
		return new QuickRepliesBuilder();
	}

	public QuickRepliesBuilder addQuickReply(String title) {
		replies.add(QuickReply.text(title));
		return this;
	}

	public QuickRepliesBuilder addQuickReply(String title, String payload) {
		replies.add(QuickReply.text(title, payload));
		return this;
	}

	@Override
	List<QuickReply> buildGeneric() {
		return replies;
	}
}
