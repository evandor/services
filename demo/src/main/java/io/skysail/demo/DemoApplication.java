package io.skysail.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		new Comment("id");
		SpringApplication.run(DemoApplication.class, args);
	}
}
