package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/webhook-reference#format">Callback Common Format</a>
 *
 * @author vgorin
 *         file created on 11/19/16 2:52 PM
 */


@XmlRootElement
public class Entry extends AbstractJson {
	@XmlElement
	public String id;
	@XmlElement
	public long time;
	@XmlElement
	public List<Messaging> messaging;
}
