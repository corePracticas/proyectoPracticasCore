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
import java.util.List;

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
		for (int i = 0; i < 10; i++) {
			Client client = new Client();
			Grue grue = new Grue();
			List<Rent> rentList = new ArrayList<>();

			// CLIENT
			client.setName("user" + i);
			client.setEmail("user" + i + "@mail.com");  // Unique email address
			client.setPassword(passwordEncoder.encode("jhon2301"));

			// Save the client first
			clientsService.create(client);

			// GRUE
			grue.setName("Grua " + i);
			grue.setType(i % 2 == 0 ? "Grua Normal": "Grua Todoterreno");
			grue.setCapacity(i * 10);
			grue.setLocation(i % 2 == 0 ? "Madrid" : "Valencia");
			grue.setAvailable(i % 2 == 0);
			grue.setPricePerMonth(i * 10);
			grue.setUpdatedAt(LocalDate.now());

			// Save the grue first
			grueService.create(grue);

			// Rent
			Rent rent = new Rent();
			rent.setStartDate(LocalDate.now().minusDays(i * 2));
			rent.setEndDate(LocalDate.now().plusDays(i * 2));
			rent.setTotalPrice(100.0 * i);
			rent.setStatus(i % 2 == 0 ? RentStatus.CONFIRMED : RentStatus.PENDING);
			rent.setCreatedAt(LocalDate.now().minusDays(1)); // Fixed value for createdAt
			rent.setUpdatedAt(LocalDate.now());
			rent.setClient(client);
			rent.setGrue(grue);
			rentList.add(rent);

			// Assign the rents list to grue
			if(i == 9){
				grue.setRents(rentList);
			}

			// Now save the rent
			rentsService.create(rent);
		}
	}
}
