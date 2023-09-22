package dev.braeuninger.evaluierungbackend.repository;

import dev.braeuninger.evaluierungbackend.repository.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
}
