package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 2/10/17 5:46 PM
 */


@XmlRootElement
public class UserProfile extends AbstractJson {
	@XmlElement(name = "first_name")
	public String firstName;
	@XmlElement(name = "last_name")
	public String lastName;
	@XmlElement(name = "profile_pic")
	public String profilePic;
	@XmlElement
	public String locale;
	@XmlElement
	public int timezone;
	@XmlElement
	public String gender;
}
