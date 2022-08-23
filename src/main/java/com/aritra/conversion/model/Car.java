package com.aritra.conversion.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Car {
    @VersioningProperties({
            @VersioningProperties.Property(
                    version = "CAMELCASE",
                    value = "carName"),
            @VersioningProperties.Property(
                    version = "PASCALCASE",
                    value = "CarName"
            )})
    private String carName;

    @VersioningProperties({
            @VersioningProperties.Property(
                    version = "CAMELCASE",
                    value = "carNumber"),
            @VersioningProperties.Property(
                    version = "PASCALCASE",
                    value = "CarNumber"
            )})
    private long carNumber;

    @VersioningProperties({
            @VersioningProperties.Property(
                    version = "CAMELCASE",
                    value = "serviceDetails"),
            @VersioningProperties.Property(
                    version = "PASCALCASE",
                    value = "ServiceDetails"
            )})
    private List<ServiceDetails> serviceDetails;
    public Car() { }
}
