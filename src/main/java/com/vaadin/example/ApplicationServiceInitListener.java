package com.vaadin.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

/**
 * This init listener is run once whenever the Vaadin context starts. As such,
 * it is a great place to create dummy data.
 * <p>
 * See the <code>application.properties</code> file for database connection
 * properties.
 */
@Service
public class ApplicationServiceInitListener implements VaadinServiceInitListener {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void serviceInit(ServiceInitEvent serviceInitEvent) {
		System.out.println("_________DB initiation has started____________");

		// Initializing tables in the database

		// First, remove if already exist
		//initDBStructure();
		// insert data
		//populateData();

		System.out.println("_________DB initiation has finished____________");
	}

	private void initDBStructure() {

		jdbcTemplate.execute("DROP TABLE movie IF EXISTS ");
		jdbcTemplate.execute("DROP TABLE director IF EXISTS ");


		jdbcTemplate.execute("CREATE TABLE grade (notenId IDENTITY NOT NULL PRIMARY KEY, wert INT)");
		jdbcTemplate.execute("CREATE TABLE db_user (benutzerId IDENTITY NOT NULL PRIMARY KEY, name VARCHAR, nachname VARCHAR,username VARCHAR, passwort VARCHAR, recht VARCHAR)");
		jdbcTemplate.execute("CREATE TABLE topics (topicId IDENTITY NOT NULL PRIMARY KEY, name VARCHAR, userId INT)");


	}

	private void populateData() {

		jdbcTemplate.update("INSERT INTO grade VALUES (DEFAULT, 5)");
		jdbcTemplate.update("INSERT INTO grade VALUES (DEFAULT, 4)");

		jdbcTemplate.update("INSERT INTO db_user VALUES (DEFAULT, 'tabea', 'reiffer', 'tabi', '$2a$12$JxwOUn6Cj6D7rx5f16vj0uKB8BFAyhW5moPJD2NMjrvUrvurQnCdG', 'ADMIN')");
		jdbcTemplate.update("INSERT INTO db_user VALUES (DEFAULT, 'sabrina', 'boccia', 'bina', '$2a$12$Z0uFdEHGGI722E3hoVTtm.csJED4INv78nWuW3DVmgVemtmBFRSay', 'ADMIN')");

	}

}
