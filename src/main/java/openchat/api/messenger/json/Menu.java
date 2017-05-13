package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author vgorin
 *         file created on 5/13/17 8:12 PM
 */


@XmlRootElement
public class Menu extends AbstractJson {
	@XmlElement
	public String locale;
	@XmlElement(name = "composer_input_disabled")
	public Boolean composerInputDisabled;
	@XmlElement(name = "call_to_actions")
	public List<MenuItem> menuItems;
}
