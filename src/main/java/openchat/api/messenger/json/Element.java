package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * A carousel item, composed of an image attachment, short description and buttons to request input from the user.
 *
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/send-api-reference/generic-template#element">element object</a>
 *
 * @author vgorin
 *         file created on 12/7/16 6:04 PM
 */


@XmlRootElement
public class Element extends AbstractJson {
	@XmlElement
	public String title;
	@XmlElement(name = "item_url")
	public String itemUrl;
	@XmlElement(name = "default_action")
	public Object defaultAction;
	@XmlElement(name = "image_url")
	public String imageUrl;
	@XmlElement
	public String subtitle;
	@XmlElement
	public List<Button> buttons;

	public Element(String title) {
		this.title = title;
	}

	public Element(String title, String imageUrl) {
		this.title = title;
		this.imageUrl = imageUrl;
	}

	public Element(String title, String itemUrl, Object defaultAction, String imageUrl, String subtitle) {
		this.title = title;
		this.itemUrl = itemUrl;
		this.defaultAction = defaultAction;
		this.imageUrl = imageUrl;
		this.subtitle = subtitle;
	}
}
