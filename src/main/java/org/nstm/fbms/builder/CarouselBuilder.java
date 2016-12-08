package org.nstm.fbms.builder;

import org.nstm.fbms.json.Attachment;
import org.nstm.fbms.json.Element;
import org.nstm.fbms.json.Message;
import org.nstm.fbms.json.Payload;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Builder for a horizontal scrollable carousel of items,
 * each composed of an image attachment, short description and buttons to request input from the user.
 *
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/send-api-reference/generic-template">Generic Template</a>
 * @author vgorin
 *         file created on 12/7/16 7:33 PM
 */


public class CarouselBuilder extends MessageBuilder {
	public CarouselBuilder() {
		this(-1);
	}

	public CarouselBuilder(int capacity) {
		message = new Message();
		message.attachment = new Attachment("template");
		message.attachment.payload = new Payload("generic");
		message.attachment.payload.elements = capacity > 0? new ArrayList<>(capacity): new LinkedList<>();
	}

	public static CarouselBuilder builder() {
		return new CarouselBuilder();
	}

	public static CarouselBuilder builder(int capacity) {
		return new CarouselBuilder(capacity);
	}

	public CarouselItemBuilder createElement(String title) {
		return new CarouselItemBuilder(this, title);
	}

	public CarouselBuilder addElement(Element element) {
		message.attachment.payload.elements.add(element);
		return this;
	}

}
