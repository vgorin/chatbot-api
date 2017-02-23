package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 11/19/16 4:26 PM
 */


@XmlRootElement
public class Response extends AbstractJson {
	@XmlElement(name = "recipient_id")
	public String recipientId;
	@XmlElement(name = "message_id")
	public String messageId;
	@XmlElement
	public String result;
}
