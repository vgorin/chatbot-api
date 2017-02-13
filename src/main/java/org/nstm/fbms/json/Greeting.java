package org.nstm.fbms.json;

/**
 * @author vgorin
 *         file created on 2/10/17 4:44 PM
 */


public class Greeting extends AbstractJson {
	public String text;

	public Greeting(String text) {
		this.text = text;
	}
}
