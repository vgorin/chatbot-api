package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 11/19/16 2:48 PM
 */


@XmlRootElement
public class Referral extends AbstractJson {
	@XmlElement
	public String ref;
	@XmlElement
	public String source;
	@XmlElement
	public String type;
}
