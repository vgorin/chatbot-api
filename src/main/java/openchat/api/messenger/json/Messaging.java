package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>
 * Field description:
 * <table border>
 *   <tr><th>field</th><th>Webhook Callback</th><th>Send API</th></tr>
 *   <tr><td>sender</td><td>always present</td><td>not used</td></tr>
 *   <tr><td>recipient</td><td>always present</td><td>required</td></tr>
 *   <tr><td>timestamp</td><td>always present</td><td>not used</td></tr>
 *   <tr><td>postback</td><td>optional</td><td>not used</td></tr>
 *   <tr><td>message</td><td>Message Received</td><td>required</td></tr>
 *   <tr><td>delivery</td><td>Message Delivered</td><td>not used</td></tr>
 *   <tr><td>read</td><td>Message Read</td><td>not used</td></tr>
 *   <tr><td>sender_action</td><td>not used</td><td>optional</td></tr>
 * </table>
 * </p>
 *
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/webhook-reference/message">Message Received</a>
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/webhook-reference/message-delivered">Message Delivered</a>
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/webhook-reference/message-read">Message Read</a>
 *
 * @author vgorin
 *         file created on 11/19/16 2:53 PM
 */


@XmlRootElement
public class Messaging extends AbstractJson {
	@XmlElement
	public Sender sender;
	@XmlElement
	public Recipient recipient;
	@XmlElement
	public String timestamp;
	@XmlElement
	public Postback postback;
	@XmlElement
	public Message message;
	@XmlElement
	public Delivery delivery;
	@XmlElement
	public Read read;
	/**
	 * One of: mark_seen, typing_on, typing_off
	 */
	@XmlElement(name = "sender_action")
	public String senderAction;

	public Messaging() {
	}

	public Messaging(Recipient recipient, Message message) {
		this.recipient = recipient;
		this.message = message;
	}
}
