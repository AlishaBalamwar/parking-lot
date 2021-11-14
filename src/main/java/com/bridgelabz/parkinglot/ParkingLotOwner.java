package com.bridgelabz.parkinglot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * Purpose: This class contains all behaviours of owner and implements from parkingLotObserver
 */
public class ParkingLotOwner implements ParkingLotObserver {
    private boolean isFullCapacity;
    static HashMap<Object, LocalDateTime> localDateTimeHashMap = new HashMap<>();
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd--HH:mm:ss");

    /**
     * Purpose: Storing Time of vehicle when vehicle is parked.
     * @param vehicle
     */
    public static void parkedTime(Object vehicle) {
        LocalDateTime now = LocalDateTime.now();
        localDateTimeHashMap.put(vehicle, now);
    }

    public void capacityIsFull() {
        isFullCapacity = true;
    }

    @Override
    public void capacityIsAvailable() {
        isFullCapacity = false;
    }

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }
}
