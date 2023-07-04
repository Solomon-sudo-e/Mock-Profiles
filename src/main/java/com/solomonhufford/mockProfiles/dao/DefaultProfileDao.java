package com.solomonhufford.mockProfiles.dao;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

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

import com.solomonhufford.mockProfiles.entity.Privacy;
import com.solomonhufford.mockProfiles.entity.Profile;
import com.solomonhufford.mockProfiles.entity.Status;
import com.solomonhufford.mockProfiles.entity.User_Profile;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultProfileDao implements ProfileDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired 
	private JdbcTemplate jdbcTemplateObject;
	
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }
	
	
	@Override
	public List<Profile> fetchProfile(String profile_name, String password_access) {
		//log.debug("Dao: profile_name={}", profile_name);
		//@formatter:off
		String sql = "SELECT *"
				+ " FROM profile"
				+ " WHERE profile_name"
				+ " = :profile_name";
		//@formatter:off
		
		Map<String, Object> params = new HashMap<>();
		params.put("profile_name", profile_name);
		return jdbcTemplate.query(sql, params, new RowMapper<>() {

			@Override
			public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
				String pass = Profile.builder().password_access(rs.getString("password_access")).build().toString();
				log.debug("Password = {}", pass);
				return Profile.builder()
						.profile_pk(rs.getLong("profile_pk"))
						.profile_name(rs.getString("profile_name"))		
						.biography(rs.getString("biography"))
						.status(Status.valueOf(rs.getString("status")))
						.privacy(Privacy.valueOf(rs.getString("privacy")))
						.password_access(rs.getString("password_access"))
						.build();
				
				}});
		
		
	}
	
	@Override
	public List<Profile> fetchProfiles() {
		//log.debug("Dao: profile_name={}", profile_name);
		//@formatter:off
		String sql = "SELECT *"
				+ " FROM profile"
				+ " ORDER BY "
				+ " profile_name";
		//@formatter:off
		
		return jdbcTemplate.query(sql, new RowMapper<>() {

			@Override
			public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
				return Profile.builder()
						.profile_pk(rs.getLong("profile_pk"))
						.profile_name(rs.getString("profile_name"))
						.biography(rs.getString("biography"))
						.privacy(Privacy.valueOf(rs.getString("privacy")))
						.build();
				}});
		
		
	}

	@Override
	public Profile updateProfile(Profile profile) {
		SqlParams params = generateUpdateSql(profile);
	
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(params.sql, params.source, keyHolder);
		
		return Profile.builder()
				.profile_pk(profile.getProfile_pk())
				.profile_name(profile.getProfile_name())
				.biography(profile.getBiography())
				.status(Status.valueOf(profile.getStatus().toString()))
				.build();
	}
	
	private SqlParams generateUpdateSql(Profile profile) {
		//@formatter:off
		String sql = ""
				+ "UPDATE profile "
				+ "SET profile_name = :profile_name, "
				+ "biography = :biography, "
				+ "status = :status "
				+ "privacy = :privacy "
				+ "WHERE profile_pk = :profile_pk";
		
		
		//UPDATE profile SET profile_name = 'salmonnnn', biography = 'I am so fun and cool' WHERE profile_pk = 1;
		//@formatter:on
		SqlParams params = new SqlParams();
		params.sql = sql;
		params.source.addValue("profile_name", profile.getProfile_name());
		params.source.addValue("biography", profile.getBiography());
		params.source.addValue("status", profile.getStatus().toString());		
		params.source.addValue("profile_pk", profile.getProfile_pk());
		params.source.addValue("privacy", profile.getPrivacy().toString());		
		return params;
	}

	@Override
	public Profile fetchProfileForCreation(Profile profile) {
		SqlParams params = generateInsertSql(profile);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(params.sql, params.source, keyHolder);
		Long profile_Pk = keyHolder.getKey().longValue();
		jdbcTemplate.update(params.sql, params.source, keyHolder);
		//@formatter:off
		return Profile.builder()
				.profile_pk(profile_Pk)
				.profile_name(profile.getProfile_name())
				.biography(profile.getBiography())
				.status(Status.valueOf(profile.getStatus().toString()))
				.privacy(Privacy.valueOf(profile.getPrivacy().toString()))
				.build();
		//@formatter:on
	}
	
	private SqlParams generateInsertSql(Profile profile) {
		//@formatter:off
				String sql = ""
						+ "INSERT INTO profile ("
						+ "profile_name, biography, status, privacy"
						+ ") VALUES ("
						+ ":profile_name, :biography, :status, :privacy"
						+ ")";
				//@formatter:on
				
				SqlParams params = new SqlParams();
				params.sql = sql;
				params.source.addValue("profile_name", profile.getProfile_name());
				params.source.addValue("biography", profile.getBiography());
				params.source.addValue("status", profile.getStatus().toString());
				params.source.addValue("privacy", profile.getPrivacy().toString());		
				return params;
	}
	
	class SqlParams {
		String sql;
		MapSqlParameterSource source = new MapSqlParameterSource();
	}
	
	@Override
	
	public List<Profile> createProfileResponseWithoutPassword(String profile_name) {
		//@formatter:off
		String sql = "SELECT *"
				+ " FROM profile"
				+ " WHERE profile_name"
				+ " = :profile_name";
		//@formatter:off
		
			Map<String, Object> params = new HashMap<>();
			params.put("profile_name", profile_name);
			return jdbcTemplate.query(sql, params, new RowMapper<>() {

			@Override
			public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
				String pass = Profile.builder().password_access(rs.getString("password_access")).build().toString();
				log.debug("Password = {}", pass);
				return Profile.builder()
							.profile_pk(rs.getLong("profile_pk"))
							.profile_name(rs.getString("profile_name"))		
							.biography(rs.getString("biography"))
							.status(Status.valueOf(rs.getString("status")))
							.privacy(Privacy.valueOf(rs.getString("privacy")))
							.build();					
						}});


	}

	@Override
	public List<Profile> rebuildPrivateProfile(String profile_name) {
		
		//@formatter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM profile "
				+ "WHERE profile_name = :profile_name";
		//@formatter:on
		Map<String, Object> params = new HashMap<>();
		params.put("profile_name", profile_name);
		return jdbcTemplate.query(sql, params, new RowMapper<>() {

		@Override
		public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			
			return Profile.builder()
						.profile_pk(rs.getLong("profile_pk"))
						.profile_name(rs.getString("profile_name"))		
						.build();					
					}});


		}

	@Override
	public List<Profile> createPrivateProfile(String profile) {
		String profile_pk = profile.substring(profile.indexOf("profile_pk=") + 11, profile.indexOf(","));	
		//log.debug(profile_pk);
			//@formatter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM profile "
				+ "WHERE profile_pk = :profile_pk";
		//@formatter:on
		Map<String, Object> params = new HashMap<>();
		params.put("profile_pk", profile_pk);
		return jdbcTemplate.query(sql, params, new RowMapper<Profile>() {

			@Override
			public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
				return Profile.builder()
						.profile_pk(rs.getLong("profile_pk"))
						.profile_name(rs.getString("profile_name"))
						.privacy(Privacy.valueOf(rs.getString("privacy")))
						.build();
			}
		});
	}

	@Override
	public List<Profile> retrievePublicProfileAfterFilter(String profile_name) {
		//@formatter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM profile "
				+ "WHERE profile_name = :profile_name";
		//@formatter:on
		HashMap<String, Object> params = new HashMap<>();
		params.put("profile_name", profile_name);
		
		return jdbcTemplate.query(sql, params, new RowMapper<Profile>() {

			@Override
			public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
				return Profile.builder()
					.profile_pk(rs.getLong("profile_pk"))
					.profile_name(rs.getString("profile_name"))		
					.biography(rs.getString("biography"))
					.status(Status.valueOf(rs.getString("status")))
					.privacy(Privacy.valueOf(rs.getString("privacy")))
					.build();					
				
			}
		});
	}


	@Override
	public Profile getProfilePK(String profile_name) {	
		String sql = ""
				+ "SELECT * "
				+ "FROM profile "
				+ "WHERE profile_name = :profile_name";
		//formatter:on
		
		Map<String,Object> params = new HashMap<>();
		params.put("profile_name", profile_name);
			return jdbcTemplate.query(sql,  params, new ProfileResultExtractor());
		}

	class ProfileResultExtractor implements ResultSetExtractor<Profile> {
			@Override
			public Profile extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				//@formatter:off
				return Profile.builder()
						.profile_pk(rs.getLong("profile_pk"))
						.build();
				//@formatter:on
			}
			
		
		
	}	
	@Override	
	public User_Profile addUsersOrUsersToProfile(String profile_pk, String user_id) {
			log.debug(profile_pk, user_id);
			SqlParams params = generateAddUsersToProfileStatement(profile_pk, user_id);
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			Long profile_pkAsLong = Long.parseLong(profile_pk);
			Long user_idAsLong = Long.parseLong(user_id);
	
			jdbcTemplate.update(params.sql, params.source, keyHolder);
			log.debug("Keyholder = {} ", keyHolder);
			
			return User_Profile.builder()
					.profile_pk(profile_pkAsLong)
					.user_id(user_idAsLong)
					.build();
		}

	protected SqlParams generateAddUsersToProfileStatement(String profile_pk, String user_id) {
		
		//@formatter:off
		String sql = "INSERT INTO user_profile "
				+ "(user_id, profile_pk) "
				+ "VALUES (:user_id, :profile_pk)";
		//@formatter:on
		SqlParams params = new SqlParams();
		params.sql = sql;
		params.source.addValue("user_id", user_id);
		params.source.addValue("profile_pk", profile_pk);
		
		return params;
	}

	@Override
	public void deleteProfile(String profile_name) {
		String deleteQuery = "DELETE FROM profile WHERE profile_name = ?";
		jdbcTemplateObject.update(deleteQuery, profile_name);
	}


	@Override
	public List<Profile> fetchProfileNameFromPk(String friend) {
		//@formatter:off
		String sql = "SELECT * FROM "
				+ "profile WHERE "
				+ "profile_pk = :profile_pk";
		//@formatter:on
		
		Map<String, Object> params = new HashMap<>();
		params.put("profile_pk", friend);
		
		return jdbcTemplate.query(sql, params, new RowMapper<Profile>() {

			@Override
			public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
				return Profile.builder()
						.profile_name(rs.getString("profile_name"))
						.build();
			}
		});
		
	}
}




	



	


	
	


