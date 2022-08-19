package com.aritra.conversion.controller;

import com.aritra.conversion.model.Vehicle;
import com.aritra.conversion.model.VersioningPropertiesIntrospector;
import com.aritra.conversion.service.RandomGeneratorService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/random")
public class GenerateRandomJsonController {

    @Autowired
    private RandomGeneratorService randomGeneratorService;

    @GetMapping("/{caseType}")
    public ResponseEntity<Vehicle> getRandomVehicle(@PathVariable String caseType) throws JsonProcessingException {
        Vehicle vehicle = randomGeneratorService.generateVehicles();
        ObjectMapper objectMapperVersion = new ObjectMapper();
        objectMapperVersion.registerModule(new JavaTimeModule());
        objectMapperVersion.setVisibility(
                objectMapperVersion
                        .getSerializationConfig()
                        .getDefaultVisibilityChecker()
                        .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
                        .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
        );
        objectMapperVersion.setAnnotationIntrospector(new VersioningPropertiesIntrospector(caseType));
        String str = objectMapperVersion.writeValueAsString(objectMapperVersion
                .convertValue(vehicle, new TypeReference<Vehicle>() {}));
        return new ResponseEntity(str, HttpStatus.OK);
    }
}
