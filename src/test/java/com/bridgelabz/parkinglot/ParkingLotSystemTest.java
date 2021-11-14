package com.bridgelabz.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ParkingLotSystemTest {

    Object vehicle;
    ParkingLotSystem parkingLotSystem;

    @BeforeEach
    public void setUp() throws Exception {
        vehicle = new Object();
        parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assertions.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isUnParked = parkingLotSystem.UnPark(vehicle);
            Assertions.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWhenAParkingLotIsFull_ShouldInformOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertTrue(capacityFull);
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() {
        Object vehicle2 = new Object();
        parkingLotSystem.setCapacity(2);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle2);
            boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle);
            boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
            Assertions.assertTrue(isParked1 && isParked2);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assertions.assertTrue(capacityFull);
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() {
        parkingLotSystem.setCapacity(2);
        ParkingLotOwner owner = new ParkingLotOwner();
        Object vehicle2 = new Object();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle2);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
        parkingLotSystem.UnPark(vehicle);
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertFalse(capacityFull);
    }

    @Test
    public void givenToParkVehicleByAttendant_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(2);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotAttendant.parkVehicle(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenADriverWantsToFindVehicle_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.park(vehicle);
        int indexOfVehicle = parkingLotSystem.findVehicle(vehicle);
        Object vehicleAtIndex = parkingLotSystem.findSpot(indexOfVehicle);
        Assertions.assertEquals(vehicle, vehicleAtIndex);
    }

    @Test
    public void givenAVehicleAndTimeWhenOwnerWantsToKnowTime_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.park(vehicle);
        Assertions.assertTrue(parkingLotSystem.isVehicleParked(vehicle));
    }
}
