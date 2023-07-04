package com.solomonhufford.mockProfiles.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.solomonhufford.mockProfiles.dao.DefaultProfileDao.ProfileResultExtractor;
import com.solomonhufford.mockProfiles.dao.DefaultProfileDao.SqlParams;
import com.solomonhufford.mockProfiles.entity.Gender;
import com.solomonhufford.mockProfiles.entity.Profile;
import com.solomonhufford.mockProfiles.entity.User;
import com.solomonhufford.mockProfiles.entity.User_Profile;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultUserDao implements UserDao {
	
	
	@Autowired 
	NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired 
	private JdbcTemplate jdbcTemplateObject;
	
	@Autowired
	private DataSource dataSource;
	
	
	public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }
	
	public User createUser(User userRequest) {
			SqlParams params = generateInsertSql(userRequest);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(params.sql, params.source, keyHolder);
			Long user_Pk = keyHolder.getKey().longValue();
			jdbcTemplate.update(params.sql, params.source, keyHolder);
			
			
			//@formatter:off
			return User.builder()
				.user_id(user_Pk)
				.user_name(userRequest.getUser_name())
				.date_of_birth(userRequest.getDate_of_birth())
				.age(userRequest.getAge())
				.gender(Gender.valueOf(userRequest.getGender().toString()))
				.email(userRequest.getEmail())
				.location(userRequest.getLocation())
			    .build();
	
			//@formatter:on
	}
	
	private SqlParams generateInsertSql(User userRequest) {
		//@formatter:off
				String sql = ""
						+ "INSERT INTO user_data ("
						+ "user_name, date_of_birth, age, gender, email, location"
						+ ") VALUES ("
						+ ":user_name, :date_of_birth, :age, :gender, :email, :location"
						+ ")";
				//@formatter:on
				
				SqlParams params = new SqlParams();
				params.sql = sql;
				params.source.addValue("user_name", userRequest.getUser_name());
				params.source.addValue("date_of_birth", userRequest.getDate_of_birth());
				params.source.addValue("age", userRequest.getAge());
				params.source.addValue("gender", userRequest.getGender().toString());
				params.source.addValue("email", userRequest.getEmail());
				params.source.addValue("location", userRequest.getLocation());
				return params;
	}

	class SqlParams {
		String sql;
		MapSqlParameterSource source = new MapSqlParameterSource();
	}

	@Override
	public List<User> fetchUser(String user_name) {
		//@formatter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM user_data "
				+ "WHERE user_name = :user_name";
		//@formatter:on
		Map<String, Object> params = new HashMap<>();
		params.put("user_name", user_name);
			
		return jdbcTemplate.query(sql, params, new RowMapper<>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return User.builder()
						.user_id(rs.getLong("user_id"))
						.user_name(user_name)
						.date_of_birth(rs.getString("date_of_birth"))
						.age(rs.getInt("age"))
						.email(rs.getString("email"))
						.gender(Gender.valueOf(rs.getString("gender")))
						.location(rs.getString("location"))
						.build();
			}
		});	
	}

	@Override
	public User getUserId(String user_name) {
		//@formatter:off	
		String sql = ""
				+ "SELECT * "
				+ "FROM user_data "
				+ "WHERE user_name = :user_name";
		//formatter:on
		
		Map<String,Object> params = new HashMap<>();
		params.put("user_name", user_name);
			return jdbcTemplate.query(sql,  params, new UserResultExtractor());
		}

		class UserResultExtractor implements ResultSetExtractor<User> {
			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				//@formatter:off
				return User.builder()
						.user_id(rs.getLong("user_id"))
						.build();
				//@formatter:on
			
			
		}
	}

		
		@Override
		public User updateUser(User userUpdate) {
			SqlParams params = generateUpdateSql(userUpdate);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(params.sql, params.source, keyHolder);
			return User.builder()
					.user_id(userUpdate.getUser_id())
					.user_name(userUpdate.getUser_name())
					.date_of_birth(userUpdate.getDate_of_birth())
					.email(userUpdate.getEmail())
					.age(userUpdate.getAge())
					.gender(Gender.valueOf(userUpdate.getGender().toString()))
					.location(userUpdate.getLocation())
					.build();		
		}
		
		private SqlParams generateUpdateSql(User userUpdate) {
			//@formatter:off
			String sql = ""
					+ "UPDATE user_data "
					+ "SET user_name = :user_name, "
					+ "date_of_birth = :date_of_birth, "
					+ "email = :email, "
					+ "age = :age, "
					+ "gender = :gender, "
					+ "location = :location";
			//@formatter:on
			SqlParams params = new SqlParams();
			params.sql = sql;
			params.source.addValue("user_name", userUpdate.getUser_name());
			params.source.addValue("date_of_birth", userUpdate.getDate_of_birth());
			params.source.addValue("email", userUpdate.getEmail());
			params.source.addValue("age", userUpdate.getAge());
			params.source.addValue("gender", userUpdate.getGender());
			params.source.addValue("location", userUpdate.getLocation());
			return params;
		}

		@Override
		public List<User> getUser(String user_name) {
			//@formatter:off
			String sql = "SELECT * "
					+ "FROM user_data "
					+ "WHERE user_data "
					+ "= :user_name";
			//@formatter:on
			Map<String, Object> params = new HashMap<>();
			params.put("user_name", user_name);
			return jdbcTemplate.query(sql, params, new RowMapper<User>() {

				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					rs.next();
					return User.builder()
							.user_name(user_name)
							.date_of_birth(rs.getString("date_of_birth"))
							.email(rs.getString("email"))
							.age(rs.getInt("age"))
							.gender(Gender.valueOf(rs.getString("gender")))
							.location(rs.getString("location"))
							.build();
				}
			});
		}

		@Override
		public void deleteUser(String user_name) {
			String deleteQuery = "DELETE FROM user_data WHERE user_name = ?";
			jdbcTemplateObject.update(deleteQuery, user_name);
		}

		@Override
		public User_Profile addProfileToUser(String user_id, String profile_pk) {
			SqlParams params = generateProfileToUserStatement(user_id, profile_pk);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			Long profiile_pkAsLong = Long.parseLong(profile_pk);
			Long user_idAsLong = Long.parseLong(user_id);
			
			jdbcTemplate.update(params.sql, params.source, keyHolder);
			return User_Profile.builder()
					.user_id(user_idAsLong)
					.profile_pk(profiile_pkAsLong)
					.build();
		}

	private SqlParams generateProfileToUserStatement(String user_id, String profile_pk) {
		//@formatter:off
		String sql = ""
				+ "INSERT INTO user_profile "
				+ "(user_id, profile_pk) "
				+ "VALUES (:user_id, :profile_pk)";
		//@formatter:on
		SqlParams params = new SqlParams();
		params.sql = sql;
		params.source.addValue("user_id", user_id);
		params.source.addValue("profile_pk", profile_pk);
		return params;
	}

			
	}
	

	
	





