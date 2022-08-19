package com.aritra.conversion.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ServiceDetails {
    @VersioningProperties({
            @VersioningProperties.Property(
                    version = "CAMELCASE",
                    value = "timestamp"),
            @VersioningProperties.Property(
                    version = "PASCALCASE",
                    value = "Timestamp"
            )})
    private Instant timestamp;

    @VersioningProperties({
            @VersioningProperties.Property(
                    version = "CAMELCASE",
                    value = "garageName"),
            @VersioningProperties.Property(
                    version = "PASCALCASE",
                    value = "GarageName"
            )})
    private String garageName;
    public ServiceDetails() {}
}
