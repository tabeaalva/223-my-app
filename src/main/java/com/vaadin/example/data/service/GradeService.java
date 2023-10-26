package com.vaadin.example.data.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.vaadin.example.ApplicationServiceInitListener;
import com.vaadin.example.data.entity.Grade;

/**
 * Standard Spring Service class, where we access the database to get our data.
 * <p>
 * See the <code>application.properties</code> file for database connections
 * properties.
 *
 * @see ApplicationServiceInitListener Dummy data generation
 */
@Service
public class GradeService {

	/**
	 * Access point to our DB, automatically configured by Spring.
	 */
	@Autowired
	private JdbcTemplate template;

	/**
	 * @return All movies in the database, with director information.
	 */
	public List<Grade> getGrades() {

		// Use a query the sql mapper class understands
		final String sql = GradeRowMapper.SELECT_ALL;

		// Use Spring's JdbcTemplate helper class to run the sql
		final List<Grade> list = template.query(sql, new GradeRowMapper());

		return list;
	}

	/**
	 * JDBCTemplate mapper class, where we take a SQL ResultSet and parse out our
	 * Java DTO(s).
	 * <p>
	 * The ResultSet is dependent on the SQL query we do in the Service class, so we
	 * define the query here as a public constant.
	 */
	public static class GradeRowMapper implements RowMapper<Grade> {

		/**
		 * SQL clause to fetch all Movies from the DB, with Director names included.
		 */
		public static final String SELECT_ALL = "SELECT * FROM grade";
		//public static final String SELECT_ALL = "SELECT * FROM grade mov LEFT JOIN director dir ON mov.directorId = dir.id";

		@Override
		public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {

			final Grade grade = new Grade();
			grade.setId(rs.getLong("id"));
			grade.setWert(rs.getInt("wert"));

			/*
			final Director director = new Director();
			director.setId(rs.getLong("directorId"));
			director.setName(rs.getString("name"));
			movie.setDirector(director);
			 */

			return grade;
		}
	}
}
