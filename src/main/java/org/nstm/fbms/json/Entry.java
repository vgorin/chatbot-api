package org.nstm.fbms.json;

import java.util.List;

/**
 * @see <a href="https://developers.facebook.com/docs/messenger-platform/webhook-reference#format">Callback Common Format</a>
 *
 * @author vgorin
 *         file created on 11/19/16 2:52 PM
 */


public class Entry extends AbstractJson {
	public String id;
	public long time;
	public List<Messaging> messaging;
}
