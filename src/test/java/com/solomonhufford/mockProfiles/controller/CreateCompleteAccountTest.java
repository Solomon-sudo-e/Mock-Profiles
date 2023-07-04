package com.solomonhufford.mockProfiles.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.solomonhufford.mockProfiles.controller.support.CreateAccountTestSupport;
import com.solomonhufford.mockProfiles.entity.Gender;
import com.solomonhufford.mockProfiles.entity.Profile;
import com.solomonhufford.mockProfiles.entity.Status;
import com.solomonhufford.mockProfiles.entity.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/socialmedia_schema.sql",
		"classpath:flyway/migrations/socialmedia_data.sql"},
		config = @SqlConfig(encoding = "utf-8"))
class CreateCompleteAccountTest extends CreateAccountTestSupport {

	@Test
	void testCreateUserReturnsSuccess201() {
		//Given: an account as JSON
		String userBody = createUserBody();
		String profileBody = createProfileBody();
		String uri1 = getBaseUriUsers();
		String uri2 = getBaseUriProfiles();
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);	
		HttpEntity<String> userEntity = new HttpEntity<>(userBody, headers);
		HttpEntity<String> profileEntity = new HttpEntity<>(profileBody, headers);

		//When: order is sent
		ResponseEntity<User> userCreateResponse = getRestTemplate().exchange(uri1, HttpMethod.POST, userEntity, User.class);
		ResponseEntity<Profile> profileCreateResponse = getRestTemplate().exchange(uri2, HttpMethod.POST, profileEntity, Profile.class);

		//Then: a 201 status is returned
		assertThat(userCreateResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(profileCreateResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		// and: the returned order is correct. 
		assertThat(userCreateResponse.getBody()).isNotNull();
		assertThat(profileCreateResponse.getBody()).isNotNull();
		
		User user = userCreateResponse.getBody();
		
		assertThat(user.getDate_of_birth()).isEqualTo("02252002");
		assertThat(user.getAge()).isEqualTo(21);
		assertThat(user.getGender()).isEqualTo(Gender.MALE);
		assertThat(user.getEmail()).isEqualTo("solo.hufford@gmail.com");
		assertThat(user.getLocation()).isEqualTo("Siloam Springs, Arkansas");

		
		Profile profile = profileCreateResponse.getBody();
		assertThat(profile.getProfile_name()).isEqualTo("Solomon_Hufford");
		assertThat(profile.getBiography()).isEqualTo("Creator of the project, and first test!");
		assertThat(profile.getStatus()).isEqualTo(Status.ISONLINE);

	}
	

	

}
