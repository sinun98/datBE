package dev.braeuninger.evaluierungbackend.service;

import dev.braeuninger.evaluierungbackend.domain.VehicleDto;
import dev.braeuninger.evaluierungbackend.mapper.VehicleInfoMapper;
import dev.braeuninger.evaluierungbackend.repository.VehicleRepository;
import dev.braeuninger.evaluierungbackend.repository.entity.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleInfoMapper vehicleInfoMapper;

    public void saveAllVehicles(List<VehicleDto> vehicleDtos) {
        vehicleRepository.saveAll(vehicleDtos.stream().map(vehicleInfoMapper::mapToVehicle).collect(Collectors.toList()));
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public VehicleDto getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .map(vehicleInfoMapper::mapToVehicleDto)
                .orElse(null);
    }

    public List<VehicleDto> getAllVehicles() {
        return StreamSupport.stream(vehicleRepository.findAll().spliterator(), false)
                .map(vehicleInfoMapper::mapToVehicleDto)
                .collect(Collectors.toList());
    }

}
