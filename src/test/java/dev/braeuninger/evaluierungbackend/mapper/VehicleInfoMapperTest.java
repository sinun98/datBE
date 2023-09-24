package dev.braeuninger.evaluierungbackend.mapper;

import dev.braeuninger.evaluierungbackend.domain.VehicleDto;
import dev.braeuninger.evaluierungbackend.repository.entity.Vehicle;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class VehicleInfoMapperTest {

    private VehicleInfoMapper mapper = Mappers.getMapper(VehicleInfoMapper.class);

    public void testMapToVehicle() {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(1L);
        vehicleDto.setVehicleType("Sedan");
        vehicleDto.setCreated(LocalDate.of(2022, 1, 1));
        vehicleDto.setMileage("10000 miles");
        vehicleDto.setGearbox("Automatic");
        vehicleDto.setOwner(1);
        vehicleDto.setKwAndPs("120 kW / 160 PS");
        vehicleDto.setHek(25000.0f);
        vehicleDto.setTaxation("Taxed");

        Vehicle vehicle = mapper.mapToVehicle(vehicleDto);

        assertThat(vehicle.getId()).isEqualTo(vehicleDto.getId());
        assertThat(vehicle.getVehicleType()).isEqualTo(vehicleDto.getVehicleType());
        assertThat(vehicle.getCreated()).isEqualTo(vehicleDto.getCreated());
        assertThat(vehicle.getMileage()).isEqualTo(vehicleDto.getMileage());
        assertThat(vehicle.getGearbox()).isEqualTo(vehicleDto.getGearbox());
        assertThat(vehicle.getOwner()).isEqualTo(vehicleDto.getOwner());
        assertThat(vehicle.getKwAndPs()).isEqualTo(vehicleDto.getKwAndPs());
        assertThat(vehicle.getHek()).isEqualTo(vehicleDto.getHek());
        assertThat(vehicle.getTaxation()).isEqualTo(vehicleDto.getTaxation());
    }

    @Test
    public void testMapToVehicleDto() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setVehicleType("Sedan");
        vehicle.setCreated(LocalDate.of(2022, 1, 1));
        vehicle.setMileage("10000 miles");
        vehicle.setGearbox("Automatic");
        vehicle.setOwner(1);
        vehicle.setKwAndPs("120 kW / 160 PS");
        vehicle.setHek(25000.0f);
        vehicle.setTaxation("Taxed");

        VehicleDto vehicleDto = mapper.mapToVehicleDto(vehicle);

        assertThat(vehicleDto.getId()).isEqualTo(vehicle.getId());
        assertThat(vehicleDto.getVehicleType()).isEqualTo(vehicle.getVehicleType());
        assertThat(vehicleDto.getCreated()).isEqualTo(vehicle.getCreated());
        assertThat(vehicleDto.getMileage()).isEqualTo(vehicle.getMileage());
        assertThat(vehicleDto.getGearbox()).isEqualTo(vehicle.getGearbox());
        assertThat(vehicleDto.getOwner()).isEqualTo(vehicle.getOwner());
        assertThat(vehicleDto.getKwAndPs()).isEqualTo(vehicle.getKwAndPs());
        assertThat(vehicleDto.getHek()).isEqualTo(vehicle.getHek());
        assertThat(vehicleDto.getTaxation()).isEqualTo(vehicle.getTaxation());
    }
}