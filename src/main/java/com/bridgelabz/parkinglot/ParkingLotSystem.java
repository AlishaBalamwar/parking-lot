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

    private static List vehicles;
    private static int actualCapacity;
    private static List<ParkingLotObserver> observers;

    public ParkingLotSystem() {
        this.observers = new ArrayList<>();
        this.vehicles = new ArrayList();
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

    /**
     * Purpose: To find the spot of vehicle if vehicle is present in the parking lot.
     *
     * @param vehicle-> this is vehicle which need to be find
     * @throws ParkingLotException
     * @return-> Returns the index of it.
     */
    public int findVehicle(Object vehicle) throws ParkingLotException {
        if (vehicles.contains(vehicle)) {
            ParkingLotDriver.spottedAt(vehicles.indexOf(vehicle));
            return vehicles.indexOf(vehicle);
        }
        throw new ParkingLotException("Vehicle is not Present in the lot");
    }

    /**
     * Purpose: to find the object at particular spot.
     *
     * @param vehicleFind->spot where to find vehicle
     * @return -> vehicle
     */
    public Object findSpot(int vehicleFind) {
        return vehicles.get(vehicleFind);
    }
}
