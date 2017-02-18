package openchat.api.telegram.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 2/17/17 7:20 PM
 */


@XmlRootElement
public class Update extends AbstractJson  {
	@XmlElement(name = "update_id")
	public int id;
	@XmlElement
	public Message message;
}
