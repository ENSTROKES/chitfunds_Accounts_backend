package com.application.chitfunds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.chitfunds.util.FileUploadProperties;

@SpringBootApplication
@RestController
@EnableConfigurationProperties({ FileUploadProperties.class})
@EnableMongoRepositories
public class ChitfundsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChitfundsApplication.class, args);
	}
	
	
	@GetMapping("test")
	public String test() {
		return "working fine.....";
	}
	
	
}
