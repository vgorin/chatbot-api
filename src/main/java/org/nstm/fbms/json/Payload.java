package org.nstm.fbms.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author vgorin
 *         file created on 11/19/16 5:51 PM
 */


public class Payload extends AbstractJson {
	public String url;
	@JsonProperty("template_type") public String templateType;
	public String text;
	public List<Button> buttons;
	public List<Element> elements;
	@JsonProperty("top_element_style")
	public String topElementStyle;
	public Coordinates coordinates;

	public Payload() {
	}

	public Payload(String templateType) {
		this.templateType = templateType;
	}

	public Payload(String templateType, String text) {
		this.templateType = templateType;
		this.text = text;
	}
}