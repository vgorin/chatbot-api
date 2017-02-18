package openchat.api.telegram.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 2/17/17 7:26 PM
 */


@XmlRootElement
public class Chat extends AbstractJson  {
	@XmlElement
	public int id;
	@XmlElement
	public String type;
	@XmlElement
	public String title;
	@XmlElement
	public String username;
	@XmlElement(name = "first_name")
	public String firstName;
	@XmlElement(name = "last_name")
	public String lastName;
	@XmlElement(name = "all_members_are_administrators")
	public boolean allAdmins;
}
