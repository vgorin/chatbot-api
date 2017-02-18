package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 11/19/16 5:50 PM
 */


@XmlRootElement
public class Attachment extends AbstractJson {
	@XmlElement
	public String type;
	@XmlElement
	public Payload payload;

	public Attachment() {
	}

	public Attachment(String type) {
		this.type = type;
	}

	public static Attachment image(String url) {
		Attachment a = new Attachment("image");
		a.payload = new Payload();
		a.payload.url = url;
		return a;
	}
}
