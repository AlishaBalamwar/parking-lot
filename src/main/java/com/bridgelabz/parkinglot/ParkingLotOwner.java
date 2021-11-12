package com.bridgelabz.parkinglot;

/**
 * Purpose: This class contains all behaviours of owner
 */
public class ParkingLotOwner {
    private boolean isFullCapacity;

    public void capacityIsFull() {
        isFullCapacity = true;
    }

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }
}
