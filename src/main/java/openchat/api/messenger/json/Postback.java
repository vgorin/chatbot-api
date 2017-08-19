package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 11/19/16 2:48 PM
 */


@XmlRootElement
public class Postback extends AbstractJson {
	@XmlElement
	public String title;
	@XmlElement
	public String payload;
	@XmlElement
	public String title;
	@XmlElement
	public Referral referral;

	public Postback() {
	}

	public Postback(String payload) {
		this(payload, payload);
	}

	public Postback(String payload, String title) {
		this.payload = payload;
		this.title = title;
	}
}
