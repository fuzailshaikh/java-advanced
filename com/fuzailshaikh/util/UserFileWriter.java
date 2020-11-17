package com.fuzailshaikh.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.fuzailshaikh.model.User;

public class UserFileWriter {
	
	public static void writeObject(String path, User user) {
		try (FileOutputStream fileOut = new FileOutputStream(path);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static User readObject(String path) {
		User user = null;

		try (FileInputStream fileIn = new FileInputStream(path);
				ObjectInputStream in = new ObjectInputStream(fileIn);) {
			user = (User) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
}
