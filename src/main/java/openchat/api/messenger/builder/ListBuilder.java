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
	public static final int COMPACT_PAGE_SIZE = 4;
	public static final int LARGE_PAGE_SIZE = 3;

	private int capacity;

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
		this.capacity = capacity;
	}

	public static ListBuilder compact() {
		return new ListBuilder("compact", COMPACT_PAGE_SIZE);
	}

	public static ListBuilder large() {
		return new ListBuilder("large", LARGE_PAGE_SIZE);
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
		if(message.attachment.payload.elements.size() < capacity) {
			message.attachment.payload.elements.add(element);
			return this;
		}
		throw new IllegalStateException(String.format("too many elements, maximum is %d", capacity));
	}

	public ListBuilder addElements(Collection<Element> elements) {
		if(message.attachment.payload.elements.size() + elements.size() > capacity) {
			throw new IllegalStateException(String.format("too many elements, maximum is %d", capacity));
		}
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

	public int size() {
		return message.attachment.payload.elements.size();
	}

	public int capacity() {
		return capacity;
	}

}
