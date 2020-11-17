package com.fuzailshaikh.config;

import java.util.Arrays;
import java.util.List;

public final class Constants {
	public static String SCHEMA_FILE_PATH = "com/fuzailshaikh/resources/input/schema.ddl";
	public static String INPUT_FILE_PATH = "com/fuzailshaikh/resources/input/users.csv";
	public static String OUT_FILE_PATH = "com/fuzailshaikh/resources/output/user_output.ser";
	public static List<String> USER_HEADER = Arrays.asList("id", "first_name", "last_name","age","country","balance");
}
