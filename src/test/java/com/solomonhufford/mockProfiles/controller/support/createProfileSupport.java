package com.solomonhufford.mockProfiles.controller.support;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.solomonhufford.mockProfiles.entity.Profile;
import com.solomonhufford.mockProfiles.entity.Status;

public class createProfileSupport extends BaseTest {
	protected List<Profile> profileExpected() {
		List<Profile> profiles = new LinkedList<>();
		//@formatter:off
		profiles.add(Profile.builder()
				.profile_pk((long) 1)
				.profile_name("Solomon_Hufford")
				.picture_id(null)
				.biography("Creator of the project, and first test!")
				.status(Status.ISONLINE)
				.build());
		
		//profiles.add(Profile.builder()
		//		.profile_pk("11")
		//		.profile_name("Trey Miles")
		//		.picture_id(null)
		//		.biography("I love playing video games!!")
		//		.status(Status.isOnline)
		//		.build());
		//@formatter:on
		
		Collections.sort(profiles);
		return profiles;
	}
	
	protected void assertErrorMessage(Map<String, Object> error, HttpStatus status) {
		//@formatter:off
		assertThat(error)
		.containsKey("message")
		.containsEntry("status code", status.value())
		.containsEntry("uri", "/profiles")
		.containsKey("timestamp")
		.containsEntry("reason", status.getReasonPhrase());
		//@formatter:on
		
		
	}
}

//private long profile_pk;
//private String profile_name;
//private String picture_id;
//private String biography;
//private Status status;