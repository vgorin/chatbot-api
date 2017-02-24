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
	public long id;

	public Recipient() {
	}

	public Recipient(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("recipient #%d", id);
	}
}
