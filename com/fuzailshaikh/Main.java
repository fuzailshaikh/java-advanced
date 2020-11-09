package com.fuzailshaikh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fuzailshaikh.config.DatabaseConfig;
import com.fuzailshaikh.model.Marker;
import com.fuzailshaikh.model.Student;

public class Main {
	private static String schemaFilePath = "com/fuzailshaikh/resources/schema.ddl";
	private static String inputFilePath = "com/fuzailshaikh/resources/data.csv";
	private static String objectOutputFilePath = "com/fuzailshaikh/output/student.ser";
	private static List<String> header = Stream.of("id", "name", "age").collect(Collectors.toList());

	public static void main(String[] args) {
		Marker markerAnnotation = Student.class.getAnnotation(Marker.class);
		System.out.println("Type of student: " + markerAnnotation.type());

		List<Student> students = readCsv(inputFilePath);

		// Write all data to database
		writeToDb(students);

		// Write an object to file
		writeObject(students.get(0));

		// Read object from file
		Student s = readObject();
		System.out.println(s);
	}

	private static void writeToDb(List<Student> students) {
		String sql = "INSERT INTO STUDENTS (ID,NAME,AGE) VALUES (?,?,?)";

		try (Connection con = DatabaseConfig.getConnection(); Statement st = con.createStatement()) {
			// Drop and recreate table in db
			recreateTable(con);

			PreparedStatement preparedStatement = con.prepareStatement(sql);
			for (Student student : students) {
				preparedStatement.setInt(1, student.id);
				preparedStatement.setString(2, student.name);
				preparedStatement.setInt(3, student.age);
				preparedStatement.addBatch();
			}

			int[] rowsAffected = preparedStatement.executeBatch();
			System.out.println("Rows affected: " + rowsAffected.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void recreateTable(Connection con) {
		try (Scanner scan = new Scanner(new File(schemaFilePath)).useDelimiter("\\Z");
				Statement stmt = con.createStatement()) {
			String ddlQuery = scan.next();
			stmt.execute(ddlQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<Student> readCsv(String inputFilePath) {
		List<Student> students = null;
		try (Stream<String> lines = Files.lines(Paths.get(inputFilePath))) {

			students = lines.skip(1) // Skip header
					.map(Main::createStudent) // Convert line to Student object
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return students;
	}

	private static Student createStudent(String line) {
		String[] content = line.split(",");
		int id = Integer.parseInt(content[header.indexOf("id")]);
		int age = Integer.parseInt(content[header.indexOf("age")]);
		String name = content[header.indexOf("name")];

		return new Student(id, name, age);
	}

	private static void writeObject(Student student) {
		try (FileOutputStream fileOut = new FileOutputStream(objectOutputFilePath);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Student readObject() {
		Student student = null;

		try (FileInputStream fileIn = new FileInputStream(objectOutputFilePath);
				ObjectInputStream in = new ObjectInputStream(fileIn);) {
			student = (Student) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return student;
	}

}