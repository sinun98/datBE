package dev.braeuninger.evaluierungbackend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private Long id;
    private String vehicleType;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate created;
    private String mileage;
    private String gearbox;
    private int owner;
    private String kwAndPs;
    private float hek;
    private String taxation;

}
