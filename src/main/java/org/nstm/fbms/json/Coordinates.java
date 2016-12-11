package org.nstm.fbms.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vgorin
 *         file created on 12/11/16 6:43 PM
 */


public class Coordinates extends AbstractJson {
	@JsonProperty("lat")
	public String latitude;
	@JsonProperty("long")
	public String longitude;
}
