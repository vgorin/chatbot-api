package org.nstm.fbms.builder;

import org.nstm.fbms.json.QuickReply;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
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
		replies.add(new QuickReply(title));
		return this;
	}

	public QuickRepliesBuilder addQuickReply(String title, String payload) {
		replies.add(new QuickReply(title, payload));
		return this;
	}

	@Override
	List<QuickReply> buildGeneric() {
		return replies;
	}
}
