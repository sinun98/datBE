package dev.braeuninger.evaluierungbackend.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.braeuninger.evaluierungbackend.domain.JsonDto;
import dev.braeuninger.evaluierungbackend.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

/**
 * Configuration class for data loading from JSON and saving to the database.
 */
@Slf4j
@Configuration
public class DataLoadConfig {

    /**
     * Initializes a CommandLineRunner to load data from a JSON file and save it to the database.
     *
     * @param vehicleService The VehicleService used for saving data to the database.
     * @return A CommandLineRunner for data loading.
     */
    @Bean
    public CommandLineRunner dataLoader(VehicleService vehicleService) {
        return args -> {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/data.json");
            try {
                JsonDto jsonDto = objectMapper.readValue(inputStream, JsonDto.class);

                vehicleService.saveAllVehicles(jsonDto.getVehicles());
                log.info("Vehicles saved!");
            } catch (IOException e) {
                log.error("Unable to save vehicles: " + e.getMessage());
            }
        };
    }
}
