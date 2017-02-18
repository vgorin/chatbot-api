package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.LinkedList;
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


@XmlRootElement
public class Message extends AbstractJson {
	@XmlElement
	public String mid;
	@XmlElement
	public String seq;
	@XmlElement
	public String text;
	@XmlElement(name = "quick_reply")
	public QuickReply quickReply;
	@XmlElement(name = "quick_replies")
	public List<QuickReply> quickReplies;
	@XmlElement(name = "is_echo")
	public String isEcho;
	@XmlElement(name = "app_id")
	public String appId;
	@XmlElement
	public String metadata;
	@XmlElement
	public List<Attachment> attachments;
	@XmlElement
	public Attachment attachment;

	public Message() {
	}

	public Message(String text) {
		this.text = text;
	}

	public Message(Attachment attachment) {
		this.attachment = attachment;
	}

	public static Message text(String text) {
		return new Message(text);
	}

	public static Message postback(String text, String buttonText) {
		return postback(text, buttonText, buttonText);
	}

	public static Message postback(String text, String buttonText, String payload) {
		return button(text, Button.createPostbackButton(buttonText, payload));
	}

	public static Message button(String text, Button button) {
		Message message = new Message();
		message.setButtons(text, button);
		return message;
	}

	public void setButtons(String text, Button... buttons) {
		setButtons(text, Arrays.asList(buttons));
	}

	public void setButtons(String text, List<Button> buttons) {
		this.text = null;
		attachment = new Attachment("template");
		attachment.payload = new Payload("button");
		attachment.payload.buttons = new LinkedList<>();
		addButtons(text, buttons);
	}

	public void addButtons(String text, Button... buttons) {
		addButtons(text, Arrays.asList(buttons));
	}

	public void addButtons(String text, List<Button> buttons) {
		if(this.text != null) {
			throw new IllegalStateException("this message already contains text");
		}
		if(attachment == null) {
			attachment = new Attachment("template");
		}
		else if(!"template".equals(attachment.type)) {
			throw new IllegalStateException(
					String.format("wrong attachment type, expected template, actual %s", attachment.type)
			);
		}

		if(attachment.payload == null) {
			attachment.payload = new Payload("button");
		}
		else if(!"button".equals(attachment.payload.templateType)) {
			throw new IllegalStateException(
					String.format("wrong payload template type, expected button, actual %s", attachment.payload.templateType)
			);
		}
		attachment.payload.text = text;
		if(attachment.payload.buttons == null) {
			attachment.payload.buttons = new LinkedList<>();
		}
		attachment.payload.buttons.addAll(buttons);
	}

}
