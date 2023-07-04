package com.solomonhufford.mockProfiles.entity;

import java.util.Comparator;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Profile implements Comparable<Profile> {
	private Long profile_pk;
	@NotNull
	@Pattern(regexp = "[\\w\\s][^0-9]*")
	private String profile_name;
	private String picture_id;	
	private Status status;
	@Length(max = 200)
	private String biography;
	private String password_access;
	private Privacy privacy;
	private String resultStatement;
	
	@JsonIgnore
	public Long getProfilePK() {
		return profile_pk;
	}

	@Override
	public int compareTo(Profile that) {
		return Comparator.comparing(Profile::getProfilePK).compare(this, that);
	}
}
