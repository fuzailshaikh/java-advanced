package com.fuzailshaikh.model;

import java.io.Serializable;

@Marker(type = "College")
public class Student implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	// Exclude id from serialization
	transient public int id;

	public String name;

	public int age;

	public Student(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [age=" + age + ", id=" + id + ", name=" + name + "]";
	}

}
