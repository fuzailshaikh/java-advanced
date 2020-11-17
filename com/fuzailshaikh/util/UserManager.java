package com.fuzailshaikh.util;

import com.fuzailshaikh.model.User;
import static com.fuzailshaikh.config.Constants.USER_HEADER;

public class UserManager {

	public static User createUserFromString(String data) {

		String[] content = data.split(",");

		User user = new User();
		user.id = Integer.parseInt(content[USER_HEADER.indexOf("id")]);
		user.age = Integer.parseInt(content[USER_HEADER.indexOf("age")]);

		String firstName = content[USER_HEADER.indexOf("first_name")];
		String lastName = content[USER_HEADER.indexOf("last_name")];
		user.name = firstName + " " + lastName;

		user.country = content[USER_HEADER.indexOf("country")];
		user.balance = Double.parseDouble(content[USER_HEADER.indexOf("balance")]);
		return user;
	}
	
}
