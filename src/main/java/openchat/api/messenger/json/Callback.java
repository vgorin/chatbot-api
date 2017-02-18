package openchat.api.messenger.json;

import openchat.util.JsonUtil;

import java.util.List;

/**
 * Root class for <a href="https://developers.facebook.com/docs/messenger-platform/webhook-reference">Webhook API</a>
 *
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/webhook-reference#format">Callback Common Format</a>
 *
 * @author vgorin
 *         file created on 11/19/16 2:51 PM
 */


public class Callback extends AbstractJson {
	public String object;
	public List<Entry> entry;

	public static Callback parse(String json) throws Exception {
		return JsonUtil.parseJson(json, Callback.class);
	}
}
