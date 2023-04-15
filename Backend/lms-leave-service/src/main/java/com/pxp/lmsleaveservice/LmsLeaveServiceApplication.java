package com.pxp.lmsleaveservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@RefreshScope
public class LmsLeaveServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsLeaveServiceApplication.class, args);
	}

}
