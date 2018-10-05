package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 12/11/16 6:43 PM
 */


@XmlRootElement
public class Coordinates extends AbstractJson {
	@XmlElement(name = "lat")
	public double latitude;
	@XmlElement(name = "long")
	public double longitude;
}
