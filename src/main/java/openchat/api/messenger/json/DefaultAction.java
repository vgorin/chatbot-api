package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 12/8/16 4:38 PM
 */


@XmlRootElement
public class DefaultAction extends Button {
	@XmlElement(name = "messenger_extensions")
	public String messengerExtensions;
	@XmlElement(name = "fallback_url")
	public String fallbackUrl;

	public DefaultAction(String url) {
		super("web_url");
		this.url = url;
	}

}
