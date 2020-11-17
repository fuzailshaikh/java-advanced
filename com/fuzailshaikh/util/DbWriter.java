package com.fuzailshaikh.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.fuzailshaikh.config.Constants;
import com.fuzailshaikh.config.DatabaseConfig;
import com.fuzailshaikh.model.User;

public class DbWriter {
	private final static String sql = "INSERT INTO Users (ID,NAME,AGE,COUNTRY,BALANCE) VALUES (?,?,?,?,?)";

	public static void writeToDb(List<User> users) {
		try (Connection con = DatabaseConfig.getConnection(); Statement st = con.createStatement()) {
			// Drop and recreate table in db
			recreateTable(con);

			PreparedStatement preparedStatement = con.prepareStatement(sql);
			for (User user : users) {
				preparedStatement.setInt(1, user.id);
				preparedStatement.setString(2, user.name);
				preparedStatement.setInt(3, user.age);
				preparedStatement.setString(4, user.country);
				preparedStatement.setDouble(5, user.balance);
				preparedStatement.addBatch();
			}

			int[] rowsAffected = preparedStatement.executeBatch();
			System.out.println("Rows affected: " + rowsAffected.length);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void recreateTable(Connection con) {
		try (Statement stmt = con.createStatement()) {
			String ddlQuery = FileReader.readFile(Constants.SCHEMA_FILE_PATH);
			stmt.execute(ddlQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
