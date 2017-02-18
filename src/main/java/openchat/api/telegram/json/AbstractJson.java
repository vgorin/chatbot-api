package openchat.api.telegram.json;

import openchat.util.JsonUtil;

/**
 * A superclass for all JSON objects.
 * Overrides {@link #toString()} method in such a way that it renders an object
 * to a pretty JSON format.
 *
 * @author vgorin
 *         file created on 2/17/17 6:44 PM
 */


abstract class AbstractJson {
	@Override
	public String toString() {
		return toPrettyJson();
	}

	public String toJson() {
		return JsonUtil.toJson(this);
	}

	public String toPrettyJson() {
		return JsonUtil.toPrettyJson(this);
	}
}
