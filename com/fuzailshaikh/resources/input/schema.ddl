DROP TABLE IF EXISTS Users;


CREATE TABLE Users (ID INT PRIMARY KEY NOT NULL,
																					NAME TEXT NOT NULL,
																					AGE INT NOT NULL,
																					COUNTRY TEXT NOT NULL,
																					BALANCE DECIMAL NOT NULL);