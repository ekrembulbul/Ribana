package com.bulbul.ribana;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RibanaApplication {

	static Logger logger = LogManager.getLogger(RibanaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RibanaApplication.class, args);

		logger.info("APPLICATION STARTED");
	}

}
