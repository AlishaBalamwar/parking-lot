package com.bridgelabz.parkinglot;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotSystemTest {

    @Test
    public void givenAVehicle_whenParked_ShouldReturnTrue(){
    ParkingLotSystem parkingLot = new ParkingLotSystem();
    Boolean isPark = parkingLot.park(new Object());
    Assert.assertTrue(isPark);
    }
}
