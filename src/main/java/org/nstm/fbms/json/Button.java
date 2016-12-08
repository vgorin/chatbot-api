package org.nstm.fbms.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
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
