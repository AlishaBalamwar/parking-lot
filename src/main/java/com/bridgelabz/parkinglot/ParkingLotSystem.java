package com.bridgelabz.parkinglot;

public class ParkingLotSystem {

    private Object vehicle;

    public ParkingLotSystem() {
    }

    public boolean park(Object vehicle) {
        if (this.vehicle != null)
            return false;
        this.vehicle = vehicle;
        return true;
    }

    public boolean UnPark(Object vehicle) {
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
}
