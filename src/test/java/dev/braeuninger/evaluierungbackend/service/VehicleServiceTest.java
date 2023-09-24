package dev.braeuninger.evaluierungbackend.service;

import dev.braeuninger.evaluierungbackend.domain.VehicleDto;
import dev.braeuninger.evaluierungbackend.mapper.VehicleInfoMapper;
import dev.braeuninger.evaluierungbackend.repository.VehicleRepository;
import dev.braeuninger.evaluierungbackend.repository.entity.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {


    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleInfoMapper vehicleInfoMapper;

    @InjectMocks
    private VehicleService vehicleService;


    @Test
    public void testSaveAllVehicles() {

        List<VehicleDto> vehicleDtos = List.of(new VehicleDto());

        when(vehicleInfoMapper.mapToVehicle(any())).thenReturn(new Vehicle());

        vehicleService.saveAllVehicles(vehicleDtos);

        verify(vehicleInfoMapper, times(vehicleDtos.size())).mapToVehicle(any());
        verify(vehicleRepository, times(1)).saveAll(any());
    }

    @Test
    public void testDeleteVehicle() {
        Long vehicleId = 1L;

        vehicleService.deleteVehicle(vehicleId);

        verify(vehicleRepository, times(1)).deleteById(vehicleId);
    }

    @Test
    public void testGetVehicleById() {
        Long vehicleId = 1L;
        VehicleDto expectedDto = new VehicleDto();
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(new Vehicle()));
        when(vehicleInfoMapper.mapToVehicleDto(any())).thenReturn(expectedDto);

        VehicleDto result = vehicleService.getVehicleById(vehicleId);

        assertEquals(expectedDto, result);
    }

    @Test
    public void testGetAllVehicles() {
        List<Vehicle> vehicles = List.of(new Vehicle());
        when(vehicleRepository.findAll()).thenReturn(vehicles);
        when(vehicleInfoMapper.mapToVehicleDto(any())).thenReturn(new VehicleDto()); // Mock mapping

        List<VehicleDto> result = vehicleService.getAllVehicles();

        assertEquals(vehicles.size(), result.size());
    }
}
