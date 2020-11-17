package com.fuzailshaikh;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fuzailshaikh.config.Constants;
import com.fuzailshaikh.core.UserProcessor;
import com.fuzailshaikh.model.Student;
import com.fuzailshaikh.model.User;
import com.fuzailshaikh.model.annotations.AccountType;
import com.fuzailshaikh.util.DbWriter;
import com.fuzailshaikh.util.FileReader;
import com.fuzailshaikh.util.UserFileWriter;

public class Main {

	public static void main(String[] args) {
		List<User> users = FileReader.loadUsers(Constants.INPUT_FILE_PATH);

		try {
			UserProcessor processor = new UserProcessor();
			List<User> processedUsers = processor.process(users);

			// Write all data to database
			DbWriter.writeToDb(processedUsers);

			// Write an object to file
			UserFileWriter.writeObject(Constants.OUT_FILE_PATH, processedUsers.get(0));

			// Read object from file
			User savedUser = UserFileWriter.readObject(Constants.OUT_FILE_PATH);
			System.out.println(savedUser);
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static void checkStudentAnnotation() {
		AccountType typeAnnotation = Student.class.getAnnotation(AccountType.class);
		System.out.println("Account type of student: " + typeAnnotation.type());
	}

}