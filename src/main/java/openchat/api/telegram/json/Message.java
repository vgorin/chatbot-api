package openchat.api.telegram.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 2/17/17 7:22 PM
 */


@XmlRootElement
public class Message extends AbstractJson  {
	@XmlElement(name = "message_id")
	public int id;
	@XmlElement
	public User from;
	@XmlElement
	public int date;
	@XmlElement
	public Chat chat;
	@XmlElement
	public String text;
}
