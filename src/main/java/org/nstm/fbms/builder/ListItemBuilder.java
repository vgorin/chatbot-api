package org.nstm.fbms.builder;

import org.nstm.fbms.json.Button;
import org.nstm.fbms.json.DefaultAction;
import org.nstm.fbms.json.Element;

import java.util.LinkedList;

/**
 * An internal helper builder for {@link ListBuilder}
 *
 * @see ListBuilder
 *
 * @author vgorin
 *         file created on 12/8/16 4:25 PM
 */


public class ListItemBuilder extends GenericBuilder<Element> {
	private ListBuilder listBuilder;
	private Element element;

	public ListItemBuilder(ListBuilder listBuilder, String pageTitle) {
		this.listBuilder = listBuilder;
		element = new Element(pageTitle);
	}

	public ListItemBuilder(String pageTitle) {
		element = new Element(pageTitle);
	}

	public static ListItemBuilder attached(ListBuilder template, String pageTitle) {
		return new ListItemBuilder(template, pageTitle);
	}

	public static ListItemBuilder detached(String pageTitle) {
		return new ListItemBuilder(pageTitle);
	}

	public ListItemBuilder setTitle(String title) {
		element.title = title;
		return this;
	}

	public ListItemBuilder setItemUrl(String itemUrl) {
		element.itemUrl = itemUrl;
		return this;
	}

	public ListItemBuilder setDefaultAction(DefaultAction defaultAction) {
		element.defaultAction = defaultAction;
		return this;
	}

	public ListItemBuilder setImageUrl(String imageUrl) {
		element.imageUrl = imageUrl;
		return this;
	}

	public ListItemBuilder setSubtitle(String subtitle) {
		element.subtitle = subtitle;
		return this;
	}

	public ListItemBuilder addButton(Button button) {
		if(element.buttons == null) {
			element.buttons = new LinkedList<>();
		}
		element.buttons.add(button);
		return this;
	}

	@Override
	Element buildGeneric() {
		return element;
	}

	public ListBuilder close() {
		listBuilder.addElement(build());
		return listBuilder;
	}

	public ListItemBuilder createNext(String title) {
		return close().createElement(title);
	}
}