package org.nstm.fbms.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vgorin
 *         file created on 11/19/16 4:26 PM
 */


public class Response extends AbstractJson {
	@JsonProperty("recipient_id") public String recipientId;
	@JsonProperty("message_id") public String messageId;
}
