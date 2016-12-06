package org.nstm.fbms.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author vgorin
 *         file created on 11/19/16 5:51 PM
 */


public class Payload {
	public String url;
	@JsonProperty("template_type") public String templateType;
	public String text;
	public List<Button> buttons;
}
