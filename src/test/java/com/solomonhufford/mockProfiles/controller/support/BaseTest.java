package com.solomonhufford.mockProfiles.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import lombok.Getter;

public class BaseTest {
	@LocalServerPort
	private int serverPort;
	
	
	@Autowired
	@Getter
	private TestRestTemplate restTemplate;
	
	protected String getBaseUriProfiles() {
		return String.format("http://localhost:%d/profiles", serverPort);
	}
	
	protected String getBaseUriUsers() {
		return String.format("http://localhost:%d/users", serverPort);
	}
}
