# OpenChat Platform by CML Team, http://cmlteam.com/ #

## Basic Usage Example (Spring-boot based RestController). ##

### 1. pom.xml ###

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
		 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>nstm</groupId>
	<artifactId>fb-bot-api-example</artifactId>
	<version>1.0-SNAPSHOT</version>

	<!-- Inherit Spring Boot version in Spring Boot dependencies -->
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.4.2.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<dependencies>
		<dependency>
			<groupId>nstm</groupId>
			<artifactId>fb-bot-api</artifactId>
			<version>1.0-SNAPSHOT</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

	</dependencies>

	<build>
		<finalName>fb-bot-api-example</finalName>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.5.1</version>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<executable>true</executable>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>versions-maven-plugin</artifactId>
				<version>2.1</version>
			</plugin>
		</plugins>
	</build>

</project>

```

### 2. RestController ###

```
package org.nstm.fbms.example;

import MessengerClient;
import ButtonsBuilder;
import CarouselBuilder;
import Callback;
import Entry;
import Messaging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


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

### 3. SpringBootApplication ###
```
package org.nstm.fbms.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "org.nstm.fbms.example")
public class ExampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}
}
```
