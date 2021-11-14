package com.bridgelabz.parkinglot;

/**
 * Purpose: This class contains all behaviours of owner and implements from parkingLotObserver
 */
public class ParkingLotOwner implements ParkingLotObserver {
    private boolean isFullCapacity;

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
