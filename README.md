# FB Messenger Platform API by NoShit Team (tm)

Basic Usage Example (Spring-boot based RestController):

```
package org.nstm.fbms.example;

import org.nstm.fbms.MessengerClient;
import org.nstm.fbms.builder.ButtonsBuilder;
import org.nstm.fbms.builder.CarouselBuilder;
import org.nstm.fbms.json.Callback;
import org.nstm.fbms.json.Entry;
import org.nstm.fbms.json.Messaging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author vgorin
 *         file created on 12/7/16 4:33 PM
 */


@RestController
public class FacebookController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final MessengerClient client = MessengerClient.defaultClient("EAAZA0fBd0Nd4BAFXjMmN18JhcEaLo0spp5PvhyoFbsyDwF2ageakFyytvRXZBre0Tii6Tnfe5fBsfW4k3hBQuDuiJiB8Mq6S2jKlYYJ4Q0d4mx2eStD6VBftWyL7VKlZA3BHqSp01ZAgbsAuarOd4grtDlTkDS9RGaaCjMAkyQZDZD");

	@RequestMapping(
			value = "/webhook",
			method = RequestMethod.GET,
			params = {"hub.mode", "hub.challenge", "hub.verify_token"},
			headers = "Accept=text/plain",
			produces = MediaType.TEXT_PLAIN_VALUE
	)
	public String processWebHook(
			@RequestParam("hub.mode") String mode,
			@RequestParam("hub.challenge") String challenge,
			@RequestParam("hub.verify_token") String token
	) {
		log.info("webhook received, mode = {}, challenge = {}, token = {}", mode, challenge, token);
		if("1".equals(token)) {
			log.info("webhook verify ok, token = {}", token);
			return challenge;
		}
		log.warn("webhook verify failed, token = {}", token);
		return String.format("wrong token! challenge = %s, token = %s", challenge, token);
	}

	@RequestMapping(
			value = "/webhook",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseBody
	public String postWebHook(@RequestBody Callback callback) throws Exception {
		log.info("callback received, {}", callback);
		for(Entry entry: callback.entry) {
			for(Messaging messaging: entry.messaging) {
				String senderId = messaging.sender.id;
				client.sendButtons(senderId, "few buttons", ButtonsBuilder.builder(2).addPostbackButton("btn1").addCallButton("+380441234567").build());
				client.send(senderId, CarouselBuilder.builder(2).createElement("page 1").setSubtitle("nothing to show").createNext("page 2").setSubtitle("really...").close().build());
			}
		}
		return "";
	}

}
```
