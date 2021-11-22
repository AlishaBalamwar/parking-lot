package com.bridgelabz.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingLotSystemTest {

    Vehicle vehicle;
    ParkingLotSystem parkingLotSystem;

    @BeforeEach
    public void setUp() throws Exception {
        vehicle = new Vehicle("MH49-1122", Vehicle.Size.SMALL, "AUDI", false, "WHITE");
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
        parkingLotSystem.setParkingLotObserver(owner);
        Vehicle vehicle1 = new Vehicle("MH49-2233", Vehicle.Size.MEDIUM, "INOVA", false, "BLUE");
        Vehicle vehicle2 = new Vehicle("MH49-3344", Vehicle.Size.SMALL, "INOVA-CRYSTA", false, "RED");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle1);
        Assertions.assertThrows(ParkingLotException.class, () -> ParkingLotSystem.park(vehicle2));
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() {
        Vehicle vehicle2 = new Vehicle("MH49-2233", Vehicle.Size.SMALL, "INOVA", false, "BLUE");
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
        parkingLotSystem.setParkingLotObserver(airportSecurity);
        Vehicle vehicle1 = new Vehicle("MH49-2233", Vehicle.Size.SMALL, "INOVA", false, "BLUE");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle1);
        Vehicle vehicle2 = new Vehicle("MH49-3344", Vehicle.Size.SMALL, "INOVA-CRYSTA", false, "RED");
        Assertions.assertThrows(ParkingLotException.class, () -> ParkingLotSystem.park(vehicle2));
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.setParkingLotObserver(owner);
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
        Assertions.assertEquals(1, indexOfVehicle);
    }

    @Test
    public void givenAVehicleAndTimeWhenOwnerWantsToKnowTime_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.park(vehicle);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String parkedTime = now.format(formatter);
        ParkingLotOwner owner = new ParkingLotOwner();
        Assertions.assertEquals(parkedTime, owner.getTime(vehicle));
    }


    @Test
    public void givenAVehicle_WhenSearchedForWhiteCar_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.park(vehicle);
        int spotNumber = ParkingLotSystem.GetPositionByColor(vehicle, "WHITE");
        Assertions.assertEquals(0, spotNumber);
    }

    @Test
    void givenWhiteVehicle_whenParks_shouldBeKnownToPolice() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.park(vehicle);
        int spotNumber = parkingLotSystem.GetPositionByColor(vehicle, "WHITE");
        boolean checkForWhiteCarsSpot = Police.whiteCarsContains(spotNumber);
        Assertions.assertTrue(checkForWhiteCarsSpot);
    }

    @Test
    void givenBlueToyotaVehicle_whenParks_shouldBeKnownToPolice() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        Vehicle vehicle1 = new Vehicle("MH49-8877", Vehicle.Size.LARGE, "Toyota",
                false, "blue");
        parkingLotSystem.park(vehicle1);
        boolean checks = Police.checkBlueToyota(vehicle1);
        Assertions.assertTrue(checks);
    }
}
