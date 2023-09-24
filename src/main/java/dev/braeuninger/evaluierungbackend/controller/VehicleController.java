package dev.braeuninger.evaluierungbackend.controller;

import dev.braeuninger.evaluierungbackend.domain.VehicleDto;
import dev.braeuninger.evaluierungbackend.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Controller class for managing vehicles.
 */
@Slf4j
@RestController
@RequestMapping("api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    /**
     * Get a list of all vehicles.
     *
     * @return ResponseEntity containing a collection of vehicle resources with links.
     */
    @CrossOrigin(origins = "http://localhost:8023")
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<VehicleDto>>> getVehicles() {
        log.info("Get-Request to api/v1/vehicles");

        List<VehicleDto> allVehicles = vehicleService.getAllVehicles();

        List<EntityModel<VehicleDto>> vehicleResources = allVehicles.stream()
                .map(this::addLinksToVehicle)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(vehicleResources));
    }

    /**
     * Delete a vehicle by its ID.
     *
     * @param id The ID of the vehicle to delete.
     * @return ResponseEntity indicating the success or failure of the deletion.
     */
    @CrossOrigin(origins = "http://localhost:8023")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        ResponseEntity<Void> result;
        log.info(String.format("Delete-Request to api/v1/vehicles/%d ", id));
        if (vehicleService.getVehicleById(id) != null) {
            vehicleService.deleteVehicle(id);
            log.info(String.format("Vehicle with ID %d deleted successfully", id));
            result = ResponseEntity.noContent()
                    .build();
        } else {
            result = ResponseEntity.notFound()
                    .build();
        }
        return result;
    }

    /**
     * Add self link to a vehicle resource.
     *
     * @param vehicle The vehicle DTO to add a self link to.
     * @return EntityModel of the vehicle with a self link.
     */
    private EntityModel<VehicleDto> addLinksToVehicle(VehicleDto vehicle) {
        Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).deleteVehicle(vehicle.getId()))
                .withRel("delete");

        return EntityModel.of(vehicle, selfLink);
    }
}
