package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 2/10/17 4:45 PM
 */


@XmlRootElement
public class Settings extends AbstractJson {
	@XmlElement(name = "setting_type")
	public String settingType;
	@XmlElement
	public Greeting greeting;
	@XmlElement(name = "thread_state")
	public String threadState;
	@XmlElement(name = "call_to_actions")
	public Postback[] callToActions;

	public Settings(String settingType) {
		this.settingType = settingType;
	}

	public Settings(String settingType, Greeting greeting) {
		this.settingType = settingType;
		this.greeting = greeting;
	}

	public Settings(String settingType, String threadState, String payload) {
		this.settingType = settingType;
		this.threadState = threadState;
		this.callToActions = new Postback[] {new Postback(payload)};
	}

	public static Settings createGreeting(String text) {
		return new Settings("greeting", new Greeting(text));
	}

	public static Settings createGetStartedButton(String payload) {
		return new Settings(
				"call_to_actions",
				"new_thread",
				payload
		);
	}
}
