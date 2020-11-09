package com.fuzailshaikh.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
	private static final String url = "jdbc:postgresql://localhost/testdb";
	private static final String user = "fuzailshaikh";
	private static final String password = "";

	/**
	 * Connect to the PostgreSQL database
	 *
	 * @return a Connection object
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the PostgreSQL server successfully.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

}