package com.bridgelabz.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ParkingLotSystemTest {

    Vehicle vehicle;
    ParkingLotSystem parkingLotSystem;

    @BeforeEach
    public void setUp() throws Exception {
        vehicle = new Vehicle("MH49-1122", "AUDI",false,"WHITE");
        parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.unPark(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenWhenAParkingLotIsFull_ShouldInformOwner() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        Vehicle vehicle1 = new Vehicle("MH49-2233","INOVA",false,"BLUE");
        Vehicle vehicle2 = new Vehicle("MH49-3344","INOVA-CRYSTA",false,"RED");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle1);
        Assertions.assertThrows(ParkingLotException.class,()-> ParkingLotSystem.park(vehicle2));
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() {
        Vehicle vehicle2 = new Vehicle("MH49-2233","INOVA",false,"BLUE");
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
    public void givenWhenParkingLotIsFull_ShouldInformSecurity() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        Vehicle vehicle1 = new Vehicle("MH49-2233","INOVA",false,"BLUE");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle1);
        Vehicle vehicle2 = new Vehicle("MH49-3344","INOVA-CRYSTA",false,"RED");
        Assertions.assertThrows(ParkingLotException.class,()-> ParkingLotSystem.park(vehicle2));
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
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
        Assertions.assertEquals(0, indexOfVehicle);
    }

    @Test
    public void givenAVehicleAndTimeWhenOwnerWantsToKnowTime_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.park(vehicle);
        String parkedTime = ParkingLotSystem.getParkTime(vehicle);
        Assertions.assertEquals(ParkingLotSystem.getDateTime(), parkedTime);
    }

    @Test
    public void givenAVehicleIfDriverIsHandicapped_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(4);
        Vehicle vehicle1 = new Vehicle("MH49-2233","INOVA",false,"BLUE");
        Vehicle vehicle2 = new Vehicle("MH49-3344","INOVA-CRYSTA",false,"RED");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        Assertions.assertTrue(parkingLotSystem.isVehicleParked(vehicle2));
    }
}
