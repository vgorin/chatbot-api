package openchat.api.telegram.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 2/17/17 7:24 PM
 */


@XmlRootElement
public class User extends AbstractJson  {
	@XmlElement
	public int id;
	@XmlElement(name = "first_name")
	public String firstName;
	@XmlElement(name = "last_name")
	public String lastName;
	@XmlElement
	public String username;
}
