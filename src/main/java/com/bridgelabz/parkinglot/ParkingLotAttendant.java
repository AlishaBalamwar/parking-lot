package com.bridgelabz.parkinglot;

/**
 * This is attendant class which help us park vehicle as per need.
 */
public class ParkingLotAttendant {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    public void parkVehicle( Vehicle vehicle) throws ParkingLotException {
        parkingLotSystem.park(vehicle);
    }
}
