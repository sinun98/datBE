package dev.braeuninger.evaluierungbackend.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Entity class representing information about a vehicle stored in the database.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String vehicleType;
    private LocalDate created;
    private String mileage;
    private String gearbox;
    private int owner;
    private String kwAndPs;
    private float hek;
    private String taxation;
}
