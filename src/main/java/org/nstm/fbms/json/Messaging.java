package org.nstm.fbms.json;

import com.fasterxml.jackson.annotation.JsonProperty;

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


public class Messaging extends AbstractJson {
	public Sender sender;
	public Recipient recipient;
	public String timestamp;
	public Postback postback;
	public Message message;
	public Delivery delivery;
	public Read read;
	/**
	 * One of: mark_seen, typing_on, typing_off
	 */
	@JsonProperty("sender_action")
	public String senderAction;

	public Messaging() {
	}

	public Messaging(Recipient recipient, Message message) {
		this.recipient = recipient;
		this.message = message;
	}
}
