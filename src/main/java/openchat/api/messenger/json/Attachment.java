package openchat.api.messenger.json;

/**
 * @author vgorin
 *         file created on 11/19/16 5:50 PM
 */


public class Attachment extends AbstractJson {
	public String type;
	public Payload payload;

	public Attachment() {
	}

	public Attachment(String type) {
		this.type = type;
	}
}
