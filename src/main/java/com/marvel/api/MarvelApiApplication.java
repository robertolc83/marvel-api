package com.marvel.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class MarvelApiApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(MarvelApiApplication.class, args);
	}

}
