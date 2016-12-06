package org.nstm.fbms.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vgorin
 *         file created on 11/19/16 5:58 PM
 */


public class Button {
	public String type;
	public String url;
	public String title;
	public String payload;
	@JsonProperty("webview_height_ratio") public String webviewHeightRatio;
}
