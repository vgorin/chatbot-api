package openchat.api.messenger.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vgorin
 *         file created on 2/10/17 5:46 PM
 */


public class UserProfile extends AbstractJson {
	@JsonProperty("first_name")
	public String firstName;
	@JsonProperty("last_name")
	public String lastName;
	@JsonProperty("profile_pic")
	public String profilePic;
	public String locale;
	public int timezone;
	public String gender;
}
