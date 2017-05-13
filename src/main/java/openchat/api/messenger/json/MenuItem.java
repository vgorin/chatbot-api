package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author vgorin
 *         file created on 5/13/17 8:17 PM
 */


@XmlRootElement
public class MenuItem extends AbstractJson {
	@XmlElement
	public String type;
	@XmlElement
	public String title;
	@XmlElement
	public String url;
	@XmlElement
	public String payload;
	@XmlElement(name = "call_to_actions")
	public List<MenuItem> nestedItems;
	@XmlElement(name = "webview_height_ratio")
	public String webviewHeightRatio;
	@XmlElement(name = "messenger_extensions")
	public Boolean messengerExtensions;
	@XmlElement(name = "fallback_url")
	public String fallbackUrl;
	@XmlElement(name = "webview_share_button")
	public String vebviewShareButton;
}
