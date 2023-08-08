package com.hanul.broovebackdev;

import com.hanul.broovebackdev.auth.User;
import com.hanul.broovebackdev.auth.UserService;
import org.aspectj.lang.annotation.After;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BrooveBackDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrooveBackDevApplication.class, args);
	}

}
