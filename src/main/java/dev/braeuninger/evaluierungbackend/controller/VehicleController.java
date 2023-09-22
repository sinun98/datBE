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

@Slf4j
@RestController
@RequestMapping("api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @CrossOrigin(origins = "http://localhost:8023")
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<VehicleDto>>> getVehicles(){
        log.info("Get-Request to api/v1/vehicles");

        List<VehicleDto> allVehicles = vehicleService.getAllVehicles();

        List<EntityModel<VehicleDto>> productResources = allVehicles.stream()
                .map(this::addLinksToProduct)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<VehicleDto>> response = CollectionModel.of(productResources);

        return ResponseEntity.ok(response);

    }

    private EntityModel<VehicleDto> addLinksToProduct(VehicleDto product) {
        Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).deleteVehicle(product.getId()))
                .withRel("delete");

        return EntityModel.of(product, selfLink);
    }

    @CrossOrigin(origins = "http://localhost:8023")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteVehicle(@PathVariable Long id){
        ResponseEntity<Void> result;
        log.info(String.format("Delete-Request to api/v1/vehicles/%d ", id));
        if (vehicleService.getVehicleById(id) != null) {
            vehicleService.deleteVehicle(id);
            log.info(String.format("Vehicle with id %d deleted successfully", id));
            result = ResponseEntity.noContent()
                    .build();
        } else {
            result =  ResponseEntity.notFound()
                    .build();
        }
        return result;

    }
}
