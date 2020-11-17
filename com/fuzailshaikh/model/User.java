package com.fuzailshaikh.model;

import java.io.Serializable;
import com.fuzailshaikh.model.annotations.AccountType;

@AccountType
public class User implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	// Exclude id from serialization
	transient public int id;

	public String name, country;
	public int age;
	public double balance;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", country=" + country + ", age=" + age + ", balance=" + balance + "]";
	}
	
}
