package com.solomonhufford.mockProfiles.controller;
/*
 * FIX OPERATION SUMMARIES
 */
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.solomonhufford.mockProfiles.entity.Profile;
import com.solomonhufford.mockProfiles.entity.User_Profile;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Validated
@RequestMapping("/profiles")
@OpenAPIDefinition(info = @Info(title = "Profile Network"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server.")
})
@RestController
public interface ProfileController {
	//@formatter:off
	@Operation(
			summary = "Get, post, put, delete, addUser mapping",
			description = "This operation set is for basic CRUD operations and addUser operations."
					+ "CRUD statements and a addUser mapping to add users ass active conncetions.",
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "A profile is returned", 
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Profile.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "The request parameters are invalid", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "404", 
							description = "No profiles were found with the input criteria", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "500", 
							description = "An unplanned error occured", 
							content = @Content(mediaType = "application/json")),
			},
			parameters = {
					@Parameter(
							name = "profile_name", 
							allowEmptyValue = false, 
							required = true, 
							description = "The primary key for the profile expected."),
					@Parameter(	
							name = "Profile",
							allowEmptyValue = false,
							required = false,
							description = "the creation in JSON format."),
			}
			)
	
	@GetMapping("/profile")
	@ResponseStatus(code = HttpStatus.OK)
	List<Profile> fetchProfile(
			//Minimum of 3 in the case of 1 first name letter, 
			//underscore, then 1 last name letter. 
			@NotNull
			@Length(min = 3, max = 100)
			@Pattern(regexp = "[\\w\\s][^0-9]*")
			@RequestParam(
					required = false) String profile_name, String password_access);
		//@formatter:on
	
	@PostMapping("/profile")
	@ResponseStatus(code = HttpStatus.CREATED)
	Profile createProfile(@Valid @RequestBody Profile profile);
	
	@PutMapping("/profile")
	@ResponseStatus(code = HttpStatus.OK)
	Profile updateProfile(@RequestBody Profile profile);
	
	@PutMapping("/{profile_name}/addUser/{user_name}")
	@ResponseStatus(code = HttpStatus.OK)
	User_Profile addUserOrUsersToProfile(@PathVariable String profile_name, @PathVariable String user_name);
	
	@DeleteMapping("/profile") 
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	void deleteProfile(String profile_name);
	
	@Operation(
			summary = "Returns a list of Profiles",
			description = "Returns all Profiles based on Profile privacy.",
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "A profile is returned", 
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Profile.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "The request parameters are invalid", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "404", 
							description = "No profiles were found with the input criteria", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "500", 
							description = "An unplanned error occured", 
							content = @Content(mediaType = "application/json")),
			})
	//Created this to get a list of names for peoples profiles upon entering the URL. 
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	List<Profile> fetchProfiles();	
	
	
	/*
	 * ---------- Adding to Profile Controller ------------
	 * 		* Password access using spring security,
 	 *
	 * 		* User modification, insertion of PFP.
	 * 		
	 * 		* Restrictions in place so profiles / users
	 * 			cant access other accounts without granted
	 * 				permission or 'secured' link.
	 */
}



	
