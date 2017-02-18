package openchat.api.messenger.json;

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

	public DefaultAction(String url) {
		super("web_url");
		this.url = url;
	}

}
