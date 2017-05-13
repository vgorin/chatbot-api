package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author vgorin
 *         file created on 11/19/16 5:51 PM
 */


@XmlRootElement
public class Payload extends AbstractJson {
	@XmlElement
	public String url;
	@XmlElement(name = "template_type")
	public String templateType;
	@XmlElement
	public String text;
	@XmlElement
	public List<Button> buttons;
	@XmlElement
	public List<Element> elements;
	@XmlElement(name = "top_element_style")
	public String topElementStyle;
	@XmlElement
	public Coordinates coordinates;
	@XmlElement(name = "sticker_id")
	public String stickerId;
	@XmlElement
	public Boolean sharable;
	@XmlElement(name = "image_aspect_ratio")
	public String imageAspectRatio;

	public Payload() {
	}

	public Payload(String templateType) {
		this.templateType = templateType;
	}

	public Payload(String templateType, String text) {
		this.templateType = templateType;
		this.text = text;
	}
}
