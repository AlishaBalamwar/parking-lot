package com.bridgelabz.parkinglot;

/**
 * Purpose: This class contains all behaviours of AirportSecurity
 */
public class AirportSecurity {
    private boolean isFullCapacity;

    public void capacityIsFull() {
        isFullCapacity = true;
    }

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }
}
