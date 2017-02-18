package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 11/19/16 2:38 PM
 */


@XmlRootElement
public class Recipient extends AbstractJson {
	@XmlElement
	public String id;

	public Recipient() {
	}

	public Recipient(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("recipient #%s", id);
	}
}
