package org.nstm.fbms.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * <p>
 * Field description:
 * <table border>
 * <tr><th>field</th><th>Webhook Callback</th><th>Send API</th></tr>
 * <tr><td>mid</td><td>always present</td><td>not used</td></tr>
 * <tr><td>seq</td><td>always present</td><td>not used</td></tr>
 * <tr><td>text</td><td>optional</td><td>optional</td></tr>
 * <tr><td>quick_reply</td><td>optional</td><td>not used</td></tr>
 * <tr><td>quick_replies</td><td>not used</td><td>optional</td></tr>
 * <tr><td>is_echo</td><td>Message Echo</td><td>not used</td></tr>
 * <tr><td>app_id</td><td>Message Echo</td><td>not used</td></tr>
 * <tr><td>metadata</td><td>optional</td><td>optional</td></tr>
 * <tr><td>attachments</td><td>optional</td><td>not used</td></tr>
 * <tr><td>attachment</td><td>not used</td><td>optional</td></tr>
 * </table>
 * </p>
 *
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/webhook-reference/message">Message Received</a>
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/webhook-reference/message-delivered">Message Delivered</a>
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/webhook-reference/message-read">Message Read</a>
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/webhook-reference/message-echo">Message Echo</a>

 * @author vgorin
 *         file created on 11/19/16 2:41 PM
 */


public class Message extends AbstractJson {
	public String mid;
	public String seq;
	public String text;
	@JsonProperty("quick_reply")
	public QuickReply quickReply;
	@JsonProperty("quick_replies")
	public List<QuickReply> quickReplies;
	@JsonProperty("is_echo")
	public String isEcho;
	@JsonProperty("app_id")
	public String appId;
	public String metadata;
	public List<Attachment> attachments;
	public Attachment attachment;

	public Message() {
	}

	public Message(String text) {
		this.text = text;
	}

	public Message(Attachment attachment) {
		this.attachment = attachment;
	}
}
