package dev.braeuninger.evaluierungbackend.repository;

import dev.braeuninger.evaluierungbackend.repository.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;
/**
 * Repository interface for managing Vehicle entities in the database.
 */
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
}
