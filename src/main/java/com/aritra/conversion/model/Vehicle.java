package com.aritra.conversion.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vehicle {
    @VersioningProperties({
            @VersioningProperties.Property(
                    version = "CAMELCASE",
                    value = "totalVehicles"),
            @VersioningProperties.Property(
                    version = "PASCALCASE",
                    value = "TotalVehicles"
            )})
    private String totalVehicles;
    @VersioningProperties({
            @VersioningProperties.Property(
                    version = "CAMELCASE",
                    value = "carList"),
            @VersioningProperties.Property(
                    version = "PASCALCASE",
                    value = "CarList"
            )})
    private List<Car> carList;

    @VersioningProperties({
            @VersioningProperties.Property(
                    version = "CAMELCASE",
                    value = "truckList"),
            @VersioningProperties.Property(
                    version = "PASCALCASE",
                    value = "TruckList"
            )})
    private List<Truck> truckList;
}