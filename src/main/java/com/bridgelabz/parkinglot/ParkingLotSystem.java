package com.bridgelabz.parkinglot;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose : Class which has all parking lot behaviours
 *
 * @author : ALISHA BALAMWAR
 * @since : 09-11-2021
 */
public class ParkingLotSystem {

    private List vehicles;
    private int actualCapacity;
    private ParkingLotOwner owner;

    public ParkingLotSystem(int capacity) {
        this.vehicles = new ArrayList();
        this.actualCapacity = capacity;
    }

    /**
     * @param owner-> To make owner known to Parking Lot System and register it.
     * @return
     */
    public boolean registerOwner(ParkingLotOwner owner) {
        this.owner = owner;
        return true;
    }

    /**
     * @param capacity-> To set capacity according to need
     */
    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    /**
     * Purpose: Parks the vehicle when slot
     *
     * @param vehicle : reference type of object
     * @throws ParkingLotException
     */
    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicles.size() == this.actualCapacity) {
            owner.capacityIsFull();
            throw new ParkingLotException("Parking Lot is Full");
        }
        if (isVehicleParked(vehicle)) {
            throw new ParkingLotException("Vehicle is already parked");
        }
        this.vehicles.add(vehicle);
    }

    /**
     * Purpose: UnParks the vehicle as per the demand
     *
     * @param vehicle : reference type of object
     * @throws ParkingLotException
     */
    public boolean UnPark(Object vehicle) {
        if (vehicle == null) {
            return false;
        }
        if (this.vehicles.contains(vehicle)) {
            this.vehicles.remove(vehicle);
            return true;
        }
        return false;
    }

    /**
     * Purpose: checks whether vehicle is parked or not
     *
     * @param vehicle
     * @return
     */
    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicles.contains(vehicle))
            return true;
        return false;
    }
}
