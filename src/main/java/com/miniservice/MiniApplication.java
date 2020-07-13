package com.miniservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.net.UnknownHostException;

@SpringBootApplication
@EnableJpaAuditing
public class MiniApplication {
	private static Logger logger = LoggerFactory.getLogger(MiniApplication.class);

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(MiniApplication.class);
		Environment env = app.run(args).getEnvironment();


		logger.info(
				"\n**********************************************************\n\t"
						+ "Application [{}] is running! Access URLs:\n\t" 
						+ "http://localhost:{}\n\t"
						+ "**********************************************************",
				env.getProperty("spring.application.name"),
				env.getProperty("server.port"));
	}
}
