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
    private List<ParkingLotObserver> observers;

    public ParkingLotSystem(int capacity) {
        this.observers = new ArrayList<>();
        this.vehicles = new ArrayList();
        this.actualCapacity = capacity;
    }

    /**
     * @param observer: To get the observer and register it.
     * @return
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
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
        if (isVehicleParked(vehicle)) {
            throw new ParkingLotException("Vehicle is already parked");
        }
        if (this.vehicles.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Parking Lot is Full");
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
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsAvailable();
            }
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
