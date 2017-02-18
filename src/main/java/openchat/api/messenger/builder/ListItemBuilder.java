package openchat.api.messenger.builder;

import openchat.api.messenger.json.Button;
import openchat.api.messenger.json.DefaultAction;
import openchat.api.messenger.json.Element;
import org.apache.commons.lang3.StringUtils;

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
	public static final int TITLE_LENGTH = 80;
	public static final int SUBTITLE_LENGTH = 80;

	private ListBuilder listBuilder;
	private Element element;

	public ListItemBuilder(ListBuilder listBuilder, String pageTitle) {
		this.listBuilder = listBuilder;
		element = new Element(trimTitle(pageTitle));
	}

	public ListItemBuilder(String pageTitle) {
		element = new Element(trimTitle(pageTitle));
	}

	public static ListItemBuilder attached(ListBuilder template, String pageTitle) {
		return new ListItemBuilder(template, pageTitle);
	}

	public static ListItemBuilder detached(String pageTitle) {
		return new ListItemBuilder(pageTitle);
	}

	public ListItemBuilder setTitle(String title) {
		element.title = trimTitle(title);
		return this;
	}

	public ListItemBuilder setItemUrl(String itemUrl) {
		element.itemUrl = itemUrl;
		return this;
	}

	public ListItemBuilder setDefaultAction(String url) {
		element.defaultAction = new DefaultAction(url);
		return this;
	}

	public ListItemBuilder setImageUrl(String imageUrl) {
		element.imageUrl = imageUrl;
		return this;
	}

	public ListItemBuilder setSubtitle(String subtitle) {
		element.subtitle = trimSubtitle(subtitle);
		return this;
	}

	public ListItemBuilder setButton(Button button) {
		if(element.buttons == null) {
			element.buttons = new LinkedList<>();
		}
		else {
			element.buttons.clear();
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

	private static String trimTitle(String title) {
		return  StringUtils.substring(title, 0, TITLE_LENGTH);
	}

	private static String trimSubtitle(String subtitle) {
		return  StringUtils.substring(subtitle, 0, SUBTITLE_LENGTH);
	}
}