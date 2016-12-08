package org.nstm.fbms.json;

import org.nstm.fbms.JsonUtil;

/**
 * @author vgorin
 *         file created on 12/7/16 4:55 PM
 */


abstract class AbstractJson {
	@Override
	public String toString() {
		return JsonUtil.toPrettyJson(this);
	}
}
