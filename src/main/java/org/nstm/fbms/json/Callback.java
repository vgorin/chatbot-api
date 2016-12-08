package org.nstm.fbms.json;

import java.util.List;

/**
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/webhook-reference#format">Callback Common Format</a>
 *
 * @author vgorin
 *         file created on 11/19/16 2:51 PM
 */


public class Callback extends AbstractJson {
	public String object;
	public List<Entry> entry;
}
