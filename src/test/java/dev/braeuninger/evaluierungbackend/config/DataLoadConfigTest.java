package dev.braeuninger.evaluierungbackend.config;

import dev.braeuninger.evaluierungbackend.domain.VehicleDto;
import dev.braeuninger.evaluierungbackend.service.VehicleService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class DataLoadConfigTest {

    @Autowired
    private CommandLineRunner dataLoader;

    @MockBean
    private VehicleService vehicleService;

    @Test
    @SneakyThrows
    public void testDataLoader() {

        dataLoader.run();

        ArgumentCaptor<List<VehicleDto>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(vehicleService, times(2)).saveAllVehicles(argumentCaptor.capture());

        List<VehicleDto> savedVehicles = argumentCaptor.getValue();

        assertThat(savedVehicles.size()).isEqualTo(5);
    }
}
