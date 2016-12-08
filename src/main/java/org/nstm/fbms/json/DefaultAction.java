package org.nstm.fbms.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vgorin
 *         file created on 12/8/16 4:38 PM
 */


public class DefaultAction extends Button {
	@JsonProperty("messenger_extensions")
	public String messengerExtensions;
	@JsonProperty("fallback_url")
	public String fallbackUrl;

	public DefaultAction(String type) {
		super(type);
	}

	public DefaultAction(String type, String title) {
		super(type, title);
	}
}
