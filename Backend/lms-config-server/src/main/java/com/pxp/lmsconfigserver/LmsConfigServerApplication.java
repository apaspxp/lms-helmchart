package com.pxp.lmsconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class LmsConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsConfigServerApplication.class, args);
	}


	}
