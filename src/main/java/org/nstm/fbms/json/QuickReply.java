package org.nstm.fbms.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vgorin
 *         file created on 11/19/16 2:41 PM
 */


public class QuickReply extends AbstractJson {
	@JsonProperty("content_type") public String contentType;
	public String title;
	public String payload;
	@JsonProperty("image_url") public String imageUrl;

	public QuickReply() {
	}

	public QuickReply(String title) {
		this(title, title);
	}

	public QuickReply(String title, String payload) {
		this.contentType = "text";
		this.title = title;
		this.payload = payload;
	}

	public static List<QuickReply> of(String... titles) {
		List<QuickReply> replies = new ArrayList<>(titles.length);
		for(String title: titles) {
			replies.add(new QuickReply(title));
		}
		return replies;
	}
}
