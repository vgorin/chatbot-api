package org.nstm.fbms.builder;

import org.nstm.fbms.json.Attachment;
import org.nstm.fbms.json.Element;
import org.nstm.fbms.json.Message;
import org.nstm.fbms.json.Payload;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Builder for a template that allows you to present a set of items vertically.
 *
 * @see ListItemBuilder
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/send-api-reference/list-template">List Template</a>
 *
 * @author vgorin
 *         file created on 12/7/16 8:29 PM
 */


public class ListBuilder extends MessageBuilder {
	public ListBuilder() {
		this("compact");
	}


	public ListBuilder(String topElementStyle) {
		this(topElementStyle, -1);
	}

	public ListBuilder(String topElementStyle, int capacity) {
		message = new Message();
		message.attachment = new Attachment("template");
		message.attachment.payload = new Payload("list");
		message.attachment.payload.topElementStyle = topElementStyle;
		message.attachment.payload.elements = capacity > 0? new ArrayList<>(capacity): new LinkedList<>();
	}

	public static ListBuilder compact() {
		return new ListBuilder("compact");
	}

	public static ListBuilder compact(int capacity) {
		return new ListBuilder("compact", capacity);
	}

	public static ListBuilder large() {
		return new ListBuilder("large");
	}

	public static ListBuilder large(int capacity) {
		return new ListBuilder("large", capacity);
	}

	public ListItemBuilder createElement(String title) {
		return new ListItemBuilder(this, title);
	}

	public ListBuilder addElement(Element element) {
		message.attachment.payload.elements.add(element);
		return this;
	}

}
