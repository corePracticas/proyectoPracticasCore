package com.practicas.Practicas;

import com.practicas.Practicas.model.Client;
import com.practicas.Practicas.service.impl.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticasApplication implements CommandLineRunner {
	@Autowired
	private ClientsService clientsService;
	public static void main(String[] args) {
		SpringApplication.run(PracticasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Client testClient = new Client();
		testClient.setName("jhon");
		testClient.setEmail("jhon@mail.com");
		testClient.setPassword("jhon2301");
		clientsService.create(testClient);
	}
}
