package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author vgorin
 *         file created on 5/13/17 8:11 PM
 */


@XmlRootElement
public class PersistedMenu extends AbstractJson {
	@XmlElement(name = "persistent_menu")
	public List<Menu> menus;
}
