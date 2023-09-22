package dev.braeuninger.evaluierungbackend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.braeuninger.evaluierungbackend.domain.JsonDto;
import dev.braeuninger.evaluierungbackend.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;


@Slf4j
@SpringBootApplication
public class EvaluierungBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluierungBackendApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(VehicleService vehicleService){
		return args -> {

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/data.json");
			try {
				JsonDto jsonDto = objectMapper.readValue(inputStream, JsonDto.class);

				vehicleService.saveAllVehicles(jsonDto.getVehicles());
				log.info("Vehicles saved!");
			} catch (IOException e){
				log.error("Unable to save vehicles: " + e.getMessage());
			}
		};
	}
}
