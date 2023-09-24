package dev.braeuninger.evaluierungbackend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.braeuninger.evaluierungbackend.domain.VehicleDto;
import dev.braeuninger.evaluierungbackend.service.VehicleService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @SneakyThrows
    @Test
    public void testGetVehicles()  {
        List<VehicleDto> vehicleList = List.of(new VehicleDto());

        Mockito.when(vehicleService.getAllVehicles()).thenReturn(vehicleList);

        MvcResult resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/vehicles")
                .accept(MediaTypes.HAL_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaTypes.HAL_JSON))
                .andReturn();

        String content = resultActions.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(content);

        int numberOfObjectsInResponse = jsonNode.size();

        assertThat(numberOfObjectsInResponse).isEqualTo(vehicleList.size());
    }

    @SneakyThrows
    @Test
    public void testDeleteVehicle_NotFound() {

        Long vehicleId = 1L;

        Mockito.doNothing().when(vehicleService).deleteVehicle(vehicleId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/vehicles/{id}", vehicleId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

    }
}
