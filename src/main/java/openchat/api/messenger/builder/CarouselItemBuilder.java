package openchat.api.messenger.builder;

import openchat.api.messenger.json.Button;
import openchat.api.messenger.json.Element;

import java.util.LinkedList;

/**
 * An internal helper builder for {@link CarouselBuilder}
 *
 * @see CarouselBuilder
 *
 * @author vgorin
 *         file created on 12/7/16 6:36 PM
 */


public class CarouselItemBuilder extends GenericBuilder<Element> {
	private CarouselBuilder carouselBuilder;
	private Element element;

	public CarouselItemBuilder(CarouselBuilder carouselBuilder, String pageTitle) {
		this.carouselBuilder = carouselBuilder;
		element = new Element(pageTitle);
	}

	public CarouselItemBuilder(String pageTitle) {
		element = new Element(pageTitle);
	}

	public static CarouselItemBuilder attached(CarouselBuilder template, String pageTitle) {
		return new CarouselItemBuilder(template, pageTitle);
	}

	public static CarouselItemBuilder detached(String pageTitle) {
		return new CarouselItemBuilder(pageTitle);
	}

	public CarouselItemBuilder setTitle(String title) {
		element.title = title;
		return this;
	}

	public CarouselItemBuilder setItemUrl(String itemUrl) {
		element.itemUrl = itemUrl;
		return this;
	}

	public CarouselItemBuilder setDefaultAction(String defaultAction) {
		element.defaultAction = defaultAction;
		return this;
	}

	public CarouselItemBuilder setImageUrl(String imageUrl) {
		element.imageUrl = imageUrl;
		return this;
	}

	public CarouselItemBuilder setSubtitle(String subtitle) {
		element.subtitle = subtitle;
		return this;
	}

	public CarouselItemBuilder addButton(Button button) {
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

	public CarouselBuilder close() {
		carouselBuilder.addElement(build());
		return carouselBuilder;
	}

	public CarouselItemBuilder createNext(String title) {
		return close().createElement(title);
	}
}
