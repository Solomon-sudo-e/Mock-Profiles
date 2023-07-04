package com.solomonhufford.mockProfiles.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doThrow;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.solomonhufford.mockProfiles.controller.support.createProfileSupport;
import com.solomonhufford.mockProfiles.entity.Profile;
import com.solomonhufford.mockProfiles.service.ProfileService;


class createProfileTest extends createProfileSupport {
	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	@ActiveProfiles("test")
	@Sql(scripts = {"classpath:flyway/migrations/socialmedia_schema.sql",
			"classpath:flyway/migrations/socialmedia_data.sql"},
			config = @SqlConfig(encoding = "utf-8"))
	@Nested
	class TestThatAreNotPollutedByApplicationContext extends createProfileSupport {
		@Test
		void testThatAValidProfileIsReturnedWhenRequested() {
			// Given: a valid profilePK
			String profile_name = "Solomon_Hufford";
			String uri = 
					String.format("%s?profile_name=%s", getBaseUriProfiles(), profile_name);
			// When : a connection is made to the URI
			ResponseEntity<List<Profile>> response =
					getRestTemplate().exchange(uri, HttpMethod.GET, 
							null, new ParameterizedTypeReference<>() {});
			// Then: a success (OK - 200) status code is returned
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			
			// The actual 
			List<Profile> expected = profileExpected();
			
			assertThat(response.getBody()).isEqualTo(expected);
		}
		
		@Test
		void testThatAErrorMessageIsReturnedWhenAnUncreatedProfileIsRequested() {
			// Given: a valid profilePK
			String profile_name = "Cham";
			String uri = 
					String.format("%s?profile_name=%s", getBaseUriProfiles(), profile_name);
			// When : a connection is made to the URI
			ResponseEntity<Map<String, Object>> response =
					getRestTemplate().exchange(uri, HttpMethod.GET, 
							null, new ParameterizedTypeReference<>() {});
			// Then: a (404) status code is returned
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
			
			// And: a error message is returned.
			Map<String, Object> error = response.getBody();
			
			assertErrorMessage(error, HttpStatus.NOT_FOUND);
		}
		
		@ParameterizedTest
		@MethodSource("com.solomonhufford.mockProfiles.controller.createProfileTest#parametersForInvalidProfile")
		void testThatAErrorMessageIsReturnedWhenAnInvalidProfileIsRequested(String profile_name, String reason) {
			// Given: a valid profilePK
			String uri = 
					String.format("%s?profile_name=%s", getBaseUriProfiles(), profile_name);
			// When : a connection is made to the URI
			ResponseEntity<Map<String, Object>> response =
					getRestTemplate().exchange(uri, HttpMethod.GET, 
							null, new ParameterizedTypeReference<>() {});
			// Then: a (404) status code is returned
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
			
			// And: a error message is returned.
			Map<String, Object> error = response.getBody();
			
			assertErrorMessage(error, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	static Stream<Arguments> parametersForInvalidProfile() {
		return Stream.of(
				arguments("$*$*%(", "Profile Contains Non-Alpha Numeric Characters"),
				arguments("CC", "The name given was too short."),
				arguments("LegendaryTurtleMan".repeat(8), "The name is too long."),
				arguments("Coachi_Dubet3453", "Profile must not contain numbers.")
				);
		
	}
	
	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	@ActiveProfiles("test")
	@Sql(scripts = {"classpath:flyway/migrations/socialmedia_schema.sql",
			"classpath:flyway/migrations/socialmedia_data.sql"},
			config = @SqlConfig(encoding = "utf-8"))
	@Nested
	class TestThatArePollutedByApplicationContext extends createProfileSupport {
		@MockBean
		private ProfileService profileService;
		
		@Test
		void testThatAnUnplannedErrorSendsBackA500Status() {
			// Given: a valid profilePK
			String profile_name = "dohdohdoh";
			String password = "blah";
			String uri = 
					String.format("%s?profile_name=%s", getBaseUriProfiles(), profile_name);
			
			doThrow(new RuntimeException("Oof")).when(profileService).fetchProfile(profile_name, password);
			// When : a connection is made to the URI
			ResponseEntity<Map<String, Object>> response =
					getRestTemplate().exchange(uri, HttpMethod.GET, 
							null, new ParameterizedTypeReference<>() {});
			// Then: a (500) internal server error status code is returned
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
			
			// And: a error message is returned.
			Map<String, Object> error = response.getBody();
			
			assertErrorMessage(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	

	
	

}
