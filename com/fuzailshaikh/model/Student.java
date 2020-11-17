package com.fuzailshaikh.model;

import com.fuzailshaikh.model.annotations.AccountType;

@AccountType(type = "Minor")
public class Student extends User {
	/**
	 *
	 */
	private static final long serialVersionUID = 2L;

	public String school;
}
