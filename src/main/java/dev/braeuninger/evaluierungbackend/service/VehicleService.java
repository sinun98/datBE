package dev.braeuninger.evaluierungbackend.service;

import dev.braeuninger.evaluierungbackend.domain.VehicleDto;
import dev.braeuninger.evaluierungbackend.mapper.VehicleInfoMapper;
import dev.braeuninger.evaluierungbackend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service class for managing vehicle-related operations.
 */
@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleInfoMapper vehicleInfoMapper;

    /**
     * Save a list of vehicles to the database.
     *
     * @param vehicleDtos The list of VehicleDto objects to be saved.
     */
    public void saveAllVehicles(List<VehicleDto> vehicleDtos) {
        vehicleRepository.saveAll(vehicleDtos.stream().map(vehicleInfoMapper::mapToVehicle).collect(Collectors.toList()));
    }

    /**
     * Delete a vehicle by its ID.
     *
     * @param id The ID of the vehicle to be deleted.
     */
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    /**
     * Get a vehicle by its ID.
     *
     * @param id The ID of the vehicle to retrieve.
     * @return The VehicleDto representing the vehicle, or null if not found.
     */
    public VehicleDto getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .map(vehicleInfoMapper::mapToVehicleDto)
                .orElse(null);
    }

    /**
     * Get a list of all vehicles.
     *
     * @return A list of VehicleDto objects representing all vehicles in the database.
     */
    public List<VehicleDto> getAllVehicles() {
        return StreamSupport.stream(vehicleRepository.findAll().spliterator(), false)
                .map(vehicleInfoMapper::mapToVehicleDto)
                .collect(Collectors.toList());
    }
}
