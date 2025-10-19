package com.ferb.Pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args);
	}

    @EventListener(ApplicationReadyEvent.class)
    public void loadDataOnStartup() {
        // CSV data will be loaded automatically via CommandLineRunner in service
        System.out.println("🚀 Pokedex API is running! Check Swagger at http://localhost:8080/swagger-ui.html");
    }

}
