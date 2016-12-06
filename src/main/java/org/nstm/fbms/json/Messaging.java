package org.nstm.fbms.json;

/**
 * <p>
 * Field description:
 * <table border>
 *   <tr><th>field</th><th>Webhook Callback</th><th>Send API</th></tr>
 *   <tr><td>sender</td><td>always present</td><td>not used</td></tr>
 *   <tr><td>recipient</td><td>always present</td><td>required</td></tr>
 *   <tr><td>timestamp</td><td>always present</td><td>not used</td></tr>
 *   <tr><td>message</td><td>Message Received</td><td>required</td></tr>
 *   <tr><td>delivery</td><td>Message Delivered</td><td>not used</td></tr>
 *   <tr><td>read</td><td>Message Read</td><td>not used</td></tr>
 * </table>
 * </p>
 *
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/webhook-reference/message">Message Received Examples</a>
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/webhook-reference/message-delivered">Message Delivered Examples</a>
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/webhook-reference/message-read">Message Read Examples</a>
 *
 * @author vgorin
 *         file created on 11/19/16 2:53 PM
 */


public class Messaging {
	public Sender sender;
	public Recipient recipient;
	public String timestamp;
	public Message message;
	public Delivery delivery;
	public Read read;

	public Messaging() {
	}

	public Messaging(Recipient recipient, Message message) {
		this.recipient = recipient;
		this.message = message;
	}
}
