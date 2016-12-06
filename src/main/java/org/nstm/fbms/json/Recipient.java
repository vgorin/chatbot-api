package org.nstm.fbms.json;

/**
 * @author vgorin
 *         file created on 11/19/16 2:38 PM
 */


public class Recipient {
	public String id;

	public Recipient() {
	}

	public Recipient(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("recipient #%s", id);
	}
}
