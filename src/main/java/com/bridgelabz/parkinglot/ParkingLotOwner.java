package com.bridgelabz.parkinglot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * Purpose: This class contains all behaviours of owner and implements from parkingLotObserver
 */
public class ParkingLotOwner implements ParkingLotObserver {
    private boolean isFullCapacity;
    static HashMap<Object, String> parkingTime = new HashMap<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public void capacityIsFull() {
        isFullCapacity = true;
    }

    /**
     * Purpose : Storing time of vehicle into a hashmap when its park.
     *
     * @param vehicle
     */
    static void parkedTime(Vehicle vehicle) {
        LocalDateTime now = LocalDateTime.now();
        parkingTime.put(vehicle, now.format(formatter));
    }

    /**
     * Purpose : returning parking time by using vehicle as key from hash map.
     *
     * @param vehicle
     * @return parking time as string
     */
    public String getTime(Object vehicle) {
        return parkingTime.get(vehicle);
    }

    /**
     * Purpose : Removing vehicle from the list when it unparks.
     *
     * @param vehicle
     */
    public static void removeFromList(Object vehicle) {
        parkingTime.remove(vehicle);
    }

    @Override
    public void capacityIsAvailable() {
        isFullCapacity = false;
    }

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }
}
