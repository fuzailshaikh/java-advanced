package com.fuzailshaikh.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fuzailshaikh.model.User;

public class FileReader {
	public static String readFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		return Files.readString(path);
	}

	public static List<User> loadUsers(String filePath) {
		List<User> users = null;
		try (Stream<String> lines = Files.lines(Paths.get(filePath))) {

			users = lines.skip(1) // Skip header
					.map(UserManager::createUserFromString) // Convert line to User object
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}

}
