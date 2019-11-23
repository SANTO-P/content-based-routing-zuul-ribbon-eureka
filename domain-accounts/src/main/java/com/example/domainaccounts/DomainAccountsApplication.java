package com.example.domainaccounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DomainAccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DomainAccountsApplication.class, args);
	}

}
