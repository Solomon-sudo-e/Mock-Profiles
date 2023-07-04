package com.solomonhufford.mockProfiles.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.solomonhufford.mockProfiles.entity.Friend;
import com.solomonhufford.mockProfiles.entity.Profile;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.validation.constraints.NotNull;

@Validated
@RequestMapping("/profiles/users/friends")
@OpenAPIDefinition(info = @Info(title = "Profile Network"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server.")
})

@RestController
public interface FriendController {
	
		//@formatter:off
		@Operation(
				summary = "View friends, delete friends, and add friends portion.",
				description = "The operation here is set for viewing profiles friendslist, adding friends, and removing friends.",
				responses = {
						@ApiResponse(
								responseCode = "200", 
								description = "A friends list is returned", 
								content = @Content(
										mediaType = "application/json",
										schema = @Schema(implementation = Friend.class))),
						@ApiResponse(
								responseCode = "400", 
								description = "The request parameters are invalid", 
								content = @Content(mediaType = "application/json")),
						@ApiResponse(
								responseCode = "404", 
								description = "No friends were found with the for this profile.", 
								content = @Content(mediaType = "application/json")),
						@ApiResponse(
								responseCode = "500", 
								description = "An unplanned error occured", 
								content = @Content(mediaType = "application/json")),
				})
		@GetMapping
		@ResponseStatus(code = HttpStatus.OK)
		List<Profile> viewFriends(
				@NotNull
				String profile_name);
		
		//@formatter:off
		
		/*
		 * -------------------- ADDING TO CONTROLLER -----------------------
		 * 			* Add, remove, or change relationship with friends. 
		 * 				(Need to make set of enums for relationships!
		 * 			* Messages to members with accounts, potentially using
		 * 				 Kafka for practice however unsure of the practical 
		 * 					aspect of using that on a local host...
		 * 			* Pending request for a profile to need to log into a
		 * 				user in order to accept or decline.
		 */
}
