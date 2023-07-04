package com.solomonhufford.mockProfiles.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.solomonhufford.mockProfiles.entity.User;
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

@Validated
@RequestMapping("/profiles/user")
@OpenAPIDefinition(info = @Info(title = "Profile Network"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server.")
})
@RestController
public interface UserController {
	
	@Operation(
			summary = "Returns a list of users",
			description = "Returns a user given the parameters.",
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "A user is returned", 
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = User.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "The request parameters are invalid", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "404", 
							description = "No users were found with the input criteria", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "500", 
							description = "An unplanned error occured", 
							content = @Content(mediaType = "application/json")),
			},
			parameters = {
					@Parameter(
							name = "User", 
							allowEmptyValue = false, 
							required = true, 
							description = "The creation in JSON format."),
					@Parameter(
							name = "user_name",
							allowEmptyValue = false,
							required = true,
							description = "Username for each user request."
							)
					
					
			}
			)
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	User createUser(@Valid @RequestBody User userRequest);
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	User updateUser(@RequestBody User userUpdate);
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	List<User> getUser(
			@NotNull
			@Length(min = 1, max = 21)
			String user_name);
	
	@PutMapping("/{user_name}/addProfile/{profile_name}")
	@ResponseStatus(code = HttpStatus.OK)
	User_Profile addUserOrUsersToProfile(@PathVariable String user_name, @PathVariable String profile_name);
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	void deleteUser(String user_name);
		//@formatter:on
	
	/*
	 * ---------- Adding to User Controller ------------
	 * 		* Password access using spring security,
	 * 			(Also being implemented into profiles)
	 * 
	 * 		* User modification, insertion of PFP.
	 */
}
