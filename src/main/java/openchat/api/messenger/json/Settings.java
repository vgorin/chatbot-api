package openchat.api.messenger.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vgorin
 *         file created on 2/10/17 4:45 PM
 */


public class Settings extends AbstractJson {
	@JsonProperty("setting_type")
	public String settingType;
	public Greeting greeting;

	public Settings(String settingType) {
		this.settingType = settingType;
	}

	public Settings(String settingType, Greeting greeting) {
		this.settingType = settingType;
		this.greeting = greeting;
	}

	public static Settings createGreeting(String text) {
		return new Settings("greeting", new Greeting(text));
	}
}
