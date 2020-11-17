package com.fuzailshaikh.model;

import com.fuzailshaikh.model.annotations.AccountType;

@AccountType(type = "Savings")
public class Employee extends User {
	/**
	 *
	 */
	private static final long serialVersionUID = 2L;

	public String company;
}