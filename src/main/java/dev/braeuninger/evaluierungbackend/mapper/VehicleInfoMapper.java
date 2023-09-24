package dev.braeuninger.evaluierungbackend.mapper;

import dev.braeuninger.evaluierungbackend.domain.VehicleDto;
import dev.braeuninger.evaluierungbackend.repository.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper interface for converting between Vehicle and VehicleDto objects.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleInfoMapper {

    /**
     * Maps a VehicleDto object to a Vehicle object.
     *
     * @param source The VehicleDto object to be mapped.
     * @return A mapped Vehicle object.
     */
    Vehicle mapToVehicle(VehicleDto source);

    /**
     * Maps a Vehicle object to a VehicleDto object.
     *
     * @param source The Vehicle object to be mapped.
     * @return A mapped VehicleDto object.
     */
    VehicleDto mapToVehicleDto(Vehicle source);
}
