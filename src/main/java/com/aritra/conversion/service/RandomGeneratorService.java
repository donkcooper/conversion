package com.aritra.conversion.service;

import com.aritra.conversion.model.Car;
import com.aritra.conversion.model.Truck;
import com.aritra.conversion.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RandomGeneratorService {
    public Vehicle generateVehicles() {
        Random random = new Random();
        List<Car> carList = new ArrayList<>();
        List<Truck> truckList = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        for(int i = 0 ; i < 5; i++) {
           carList.add(new Car(generateRandomNames(), i));
           truckList.add(new Truck(generateRandomNames(), i));
        }
        vehicle.setTruckList(truckList);
        vehicle.setCarList(carList);
        vehicle.setTotalVehicles(Integer.toString((carList.size() + truckList.size())));
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
}
