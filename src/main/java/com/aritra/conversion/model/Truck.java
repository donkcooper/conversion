package com.aritra.conversion.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Truck {
    @VersioningProperties({
            @VersioningProperties.Property(
                    version = "CAMELCASE",
                    value = "truckName"),
            @VersioningProperties.Property(
                    version = "PASCALCASE",
                    value = "TruckName"
            )})
    private String truckName;

    @VersioningProperties({
            @VersioningProperties.Property(
                    version = "CAMELCASE",
                    value = "truckNumber"),
            @VersioningProperties.Property(
                    version = "PASCALCASE",
                    value = "TruckNumber"
            )})
    private long truckNumber;

    @VersioningProperties({
            @VersioningProperties.Property(
                    version = "CAMELCASE",
                    value = "serviceDetails"),
            @VersioningProperties.Property(
                    version = "PASCALCASE",
                    value = "ServiceDetails"
            )})
    private List<ServiceDetails> serviceDetails;
    public Truck() { }
}
