package org.nstm.fbms;

import org.nstm.fbms.json.QuickReply;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author vgorin
 *         file created on 11/19/16 6:29 PM
 */


public class QuickReplyBuilder {
	private List<QuickReply> replies;
	private boolean built = false;

	public static QuickReplyBuilder builder(int capacity) {
		return new QuickReplyBuilder(capacity);
	}

	public static QuickReplyBuilder builder() {
		return new QuickReplyBuilder();
	}

	public QuickReplyBuilder(int capacity) {
		replies = new ArrayList<>(capacity);
	}

	public QuickReplyBuilder() {
		replies = new LinkedList<>();
	}

	public void addQuickReply(String title) {
		replies.add(new QuickReply(title));
	}

	public void addQuickReply(String title, String payload) {
		replies.add(new QuickReply(title, payload));
	}

	public List<QuickReply> build() {
		if(built) {
			throw new IllegalStateException("already built!");
		}
		built = true;
		return replies;
	}
}
