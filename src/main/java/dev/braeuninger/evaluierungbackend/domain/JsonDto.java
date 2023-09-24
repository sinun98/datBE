package dev.braeuninger.evaluierungbackend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A DTO representing a JSON structure containing a list of vehicles.
 */
@Data
@NoArgsConstructor
public class JsonDto {

    /**
     * The list of vehicles contained in the JSON structure.
     */
    private List<VehicleDto> vehicles;
}
