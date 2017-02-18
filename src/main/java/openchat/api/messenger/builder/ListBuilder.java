package openchat.api.messenger.builder;

import openchat.api.messenger.json.*;

import java.util.*;

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

	public List<ListItemBuilder> createElements(Collection<String> titles) {
		List<ListItemBuilder> result = new ArrayList<>(titles.size());
		for(String title: titles) {
			result.add(createElement(title));
		}
		return result;
	}

	public List<ListItemBuilder> createElements(String... titles) {
		return createElements(Arrays.asList(titles));
	}

	public ListBuilder addElement(Element element) {
		message.attachment.payload.elements.add(element);
		return this;
	}

	public ListBuilder addElements(Collection<Element> elements) {
		message.attachment.payload.elements.addAll(elements);
		return this;
	}

	public ListBuilder addElements(Element... elements) {
		return addElements(Arrays.asList(elements));
	}

	public ListBuilder setButton(Button button) {
		if(message.attachment.payload.buttons == null) {
			message.attachment.payload.buttons = new LinkedList<>();
		}
		else {
			message.attachment.payload.buttons.clear();
		}
		message.attachment.payload.buttons.add(button);
		return this;
	}

}
