package com.aritra.conversion.service;

import com.aritra.conversion.model.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomGeneratorService {
    public Vehicle generateVehicles() {
        List<Car> carList = new ArrayList<>();
        List<Truck> truckList = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for(int i = 0 ; i < 5; i++) {
            Map<String, Object> mapValue = new LinkedHashMap<>();
            carList.add(new Car(generateRandomNames(), i, generateServiceDetails()));
            truckList.add(new Truck(generateRandomNames(), i, generateServiceDetails()));
            mapValue.put("tp", "FalconeService");
            mapValue.put("val", Integer.toString(i));
            mapValue.put("entity", generateRandomEntityList());
            mapList.add(mapValue);
        }
        vehicle.setTruckList(truckList);
        vehicle.setCarList(carList);
        vehicle.setTotalVehicles(Integer.toString((carList.size() + truckList.size())));
        Map<String, List<Map<String, Object>>> rskInptDataMap = new LinkedHashMap<>();
        rskInptDataMap.put("rskInptData", mapList);
        List<Map<String, List<Map<String, Object>>>> rskCntxtList = new ArrayList<>();
        rskCntxtList.add(rskInptDataMap);
        vehicle.setRskCntxt(rskInptDataMap);
        return vehicle;
    }

    private String generateRandomNames() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    private List<ServiceDetails> generateServiceDetails() {
        List<ServiceDetails> sdList = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            sdList.add(new ServiceDetails(timestamp(), generateRandomNames()));
        }
        return sdList;
    }

    private Instant timestamp() {
        return Instant.ofEpochSecond(ThreadLocalRandom.current().nextInt());
    }

    private Map<String, String> generateRandomEntityList() {
        Map<String, String> entityMap = new LinkedHashMap<>();
        entityMap.put("tp", "tp-value");
        entityMap.put("otherTp", "otherTp-value");
        return entityMap;
    }
}
