package dev.braeuninger.evaluierungbackend.mapper;

import dev.braeuninger.evaluierungbackend.domain.VehicleDto;
import dev.braeuninger.evaluierungbackend.repository.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleInfoMapper {

    Vehicle mapToVehicle(VehicleDto source);

    VehicleDto mapToVehicleDto(Vehicle source);

}
