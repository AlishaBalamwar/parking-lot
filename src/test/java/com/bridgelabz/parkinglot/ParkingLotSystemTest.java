package com.bridgelabz.parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

public class ParkingLotSystemTest {

    Object vehicle = null;
    ParkingLotSystem parkingLot = null;

    @Before
    public void setUp() throws Exception {
        vehicle = new Object();
        parkingLot = new ParkingLotSystem();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        boolean isPark = parkingLot.park(new Object());
        Assert.assertTrue(isPark);
    }

    @Test
    public void givenAVehicle_WhenAlreadyPark_ShouldReturnFalse() {
        parkingLot.park(vehicle);
        boolean isPark = parkingLot.park(new Object());
        Assert.assertFalse(isPark);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        parkingLot.park(vehicle);
        boolean isUnParked = parkingLot.UnPark(vehicle);
        Assert.assertTrue(isUnParked);
    }
}
