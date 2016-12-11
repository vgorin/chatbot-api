package org.nstm.fbms.json;

import com.fasterxml.jackson.annotation.JsonProperty;

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
 * @see org.nstm.fbms.builder.ButtonsBuilder
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/send-api-reference/buttons">Buttons</a>
 *
 * @author vgorin
 *         file created on 11/19/16 5:58 PM
 */


public class Button extends AbstractJson {
	public String type;
	public String url;
	public String title;
	public String payload;
	@JsonProperty("webview_height_ratio") public String webviewHeightRatio;

	public Button(String type) {
		this.type = type;
	}

	public Button(String type, String title) {
		this.type = type;
		this.title = title;
	}

	public static Button createUrlButton(String title, String url) {
		Button button = new Button("web_url", title);
		button.url = url;
		return button;
	}

	public static Button createPostbackButton(String title, String payload) {
		Button button = new Button("postback", title);
		button.payload = payload;
		return button;
	}

	public static Button createCallButton(String title, String phoneNumber) {
		Button button = new Button("phone_number", title);
		button.payload = phoneNumber;
		return button;
	}

	public static Button createShareButton() {
		return new Button("element_share");
	}
}
