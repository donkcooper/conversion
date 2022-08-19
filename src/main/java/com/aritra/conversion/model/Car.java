package com.aritra.conversion.model;

import lombok.AllArgsConstructor;
import lombok.Data;

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
    public Car() { }
}
