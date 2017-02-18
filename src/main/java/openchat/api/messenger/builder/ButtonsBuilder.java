package openchat.api.messenger.builder;

import openchat.api.messenger.json.Button;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Some <a href="https://developers.facebook.com/docs/messenger-platform/send-api-reference/templates/">templates</a>
 * as well as the <a href="https://developers.facebook.com/docs/messenger-platform/thread-settings/persistent-menu">persistent menu</a>
 * support buttons that can perform different kinds of actions:
 * <ul>
 *   <li><a href="https://developers.facebook.com/docs/messenger-platform/send-api-reference/url-button">URL Button</a>.
 *   Can be used to open a webpage in the in-app browser.</li>
 *   <li><a href="https://developers.facebook.com/docs/messenger-platform/send-api-reference/postback-button">Postback Button</a>.
 *   Sends back developer-defined payload so you can perform an action or reply back.</li>
 *   <li><a href="https://developers.facebook.com/docs/messenger-platform/send-api-reference/call-button">Call Button</a>.
 *   Dials a phone number when tapped.</li>
 *   <li><a href="https://developers.facebook.com/docs/messenger-platform/send-api-reference/share-button">Share Button</a>.
 *   Opens a share dialog in Messenger enabling people to share message bubbles with friends.</li>
 *   <li><a href="https://developers.facebook.com/docs/messenger-platform/send-api-reference/docs/messenger-platform/send-api-reference/buy-button">Buy Button</a>.
 *   Opens a checkout dialog to enables purchases.</li>
 *   <li><a href="https://developers.facebook.com/docs/messenger-platform/account-linking/link-account">Log In</a>
 *   and <a href="https://developers.facebook.com/docs/messenger-platform/account-linking/unlink-account">Log Out buttons</a>.
 *   Used in <a href="https://developers.facebook.com/docs/messenger-platform/account-linking">Account Linking</a>
 *   flow intended to deliver page-scoped user id on web safely.</li>
 * </ul>
 * </p>
 *
 * @see Button
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/send-api-reference/buttons">Buttons</a>
 *
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
