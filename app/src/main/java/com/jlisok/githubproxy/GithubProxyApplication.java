package com.jlisok.githubproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.jlisok.githubproxy")
public class GithubProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubProxyApplication.class, args);
	}

}
