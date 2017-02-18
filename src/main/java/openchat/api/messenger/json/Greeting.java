package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 2/10/17 4:44 PM
 */


@XmlRootElement
public class Greeting extends AbstractJson {
	@XmlElement
	public String text;

	public Greeting(String text) {
		this.text = text;
	}
}
