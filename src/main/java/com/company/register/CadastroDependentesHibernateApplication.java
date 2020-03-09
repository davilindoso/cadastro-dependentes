package com.company.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableFeignClients
@EnableAdminServer
public class CadastroDependentesHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroDependentesHibernateApplication.class, args);
	}

}
