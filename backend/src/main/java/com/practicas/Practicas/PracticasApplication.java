package com.practicas.Practicas;

import com.practicas.Practicas.model.Client;
import com.practicas.Practicas.model.Grue;
import com.practicas.Practicas.model.Rent;
import com.practicas.Practicas.model.enums.RentStatus;
import com.practicas.Practicas.service.impl.ClientsService;
import com.practicas.Practicas.service.impl.GrueService;
import com.practicas.Practicas.service.impl.RentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class PracticasApplication implements CommandLineRunner {
	@Autowired
	private ClientsService clientsService;
	@Autowired
	private GrueService grueService;
	@Autowired
	private RentsService rentsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(PracticasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 1; i < 11; i++) {
			Client client = new Client();
			Grue grue = new Grue();
			// CLIENT
			client.setName("user" + i);
			client.setEmail("user" + i + "@mail.com");
			client.setPassword(passwordEncoder.encode("jhon2301"));

			clientsService.create(client);

			// GRUE
			grue.setName("Grua " + i);
			grue.setType(i % 2 == 0 ? "Normal" : "Todoterreno");
			grue.setCapacity(i * 10);
			grue.setLocation(i % 2 == 0 ? "Madrid" : "Valencia");
			grue.setAvailable(i % 2 == 0);
			grue.setPricePerMonth(i * 10);
			grue.setUpdatedAt(LocalDate.now());

			grueService.create(grue);
		}

	}
}
