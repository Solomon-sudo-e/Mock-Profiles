package com.solomonhufford.mockProfiles.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.solomonhufford.mockProfiles.entity.Friend;
@Component
public class DefaultFriendDao implements FriendDao {
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Friend> viewFriendsInColumn1(String profile_pk) {
		//@formatter:off
		String sql = "SELECT * "
				+ "FROM friend "
				+ "WHERE profile_fk1 = :profile_fk1";
		//@formatter:on
		Map<String, Object> params = new HashMap<>();
		params.put("profile_fk1", profile_pk);
		return jdbcTemplate.query(sql, params, new RowMapper<Friend>() {

			@Override
			public Friend mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				return Friend.builder()
						.profile_fk2(rs.getLong("profile_fk2"))
						.build();
			}});
	}

	@Override
	public List<Friend> viewFriendsInColumn2(String profile_pk) {
		//@formatter:off
				String sql = "SELECT * "
						+ "FROM friend "
						+ "WHERE profile_fk2 = :profile_fk2";
				//@formatter:on
				Map<String, Object> params = new HashMap<>();
				params.put("profile_fk2", profile_pk);
				return jdbcTemplate.query(sql, params, new RowMapper<Friend>() {

					@Override
					public Friend mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						return Friend.builder()
								.profile_fk1(rs.getLong("profile_fk1"))
								.build();
					}});
			}
	}


