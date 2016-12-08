package org.nstm.fbms.builder;

import org.nstm.fbms.json.Button;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author vgorin
 *         file created on 12/7/16 7:59 PM
 */


public class ButtonsBuilder extends GenericBuilder<List<Button>> {
	private List<Button> buttons;

	public ButtonsBuilder() {
		buttons = new LinkedList<>();
	}

	public ButtonsBuilder(int capacity) {
		buttons = new ArrayList<>(capacity);
	}

	public static ButtonsBuilder builder() {
		return new ButtonsBuilder();
	}

	public static ButtonsBuilder builder(int capacity) {
		return new ButtonsBuilder(capacity);
	}

	public ButtonsBuilder addUrlButton(String url) {
		buttons.add(Button.createUrlButton(url, url));
		return this;
	}

	public ButtonsBuilder addUrlButton(String title, String url) {
		buttons.add(Button.createUrlButton(title, url));
		return this;
	}

	public ButtonsBuilder addPostbackButton(String title) {
		buttons.add(Button.createPostbackButton(title, title));
		return this;
	}

	public ButtonsBuilder addPostbackButton(String title, String payload) {
		buttons.add(Button.createPostbackButton(title, payload));
		return this;
	}

	public ButtonsBuilder addCallButton(String phoneNumber) {
		buttons.add(Button.createCallButton(phoneNumber, phoneNumber));
		return this;
	}

	public ButtonsBuilder addCallButton(String title, String phoneNumber) {
		buttons.add(Button.createCallButton(title, phoneNumber));
		return this;
	}

	public ButtonsBuilder addShareButton() {
		buttons.add(Button.createShareButton());
		return this;
	}

	@Override
	List<Button> buildGeneric() {
		return buttons;
	}
}
