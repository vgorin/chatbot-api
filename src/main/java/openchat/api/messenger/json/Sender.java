package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 11/19/16 2:39 PM
 */


@XmlRootElement
public class Sender extends AbstractJson {
	@XmlElement
	public long id;
}
