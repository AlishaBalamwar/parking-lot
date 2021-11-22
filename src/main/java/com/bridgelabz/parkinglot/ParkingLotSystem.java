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
    public static List<Vehicle> vehicles;
    public static List<Vehicle> parkingLot1;
    public static List<Vehicle> parkingLot2;
    private static int actualCapacity;
    private static List<ParkingLotObserver> observers;

    public ParkingLotSystem() {
        observers = new ArrayList<>();
        vehicles = new ArrayList<>();
        parkingLot1 = new ArrayList<>();
        parkingLot2 = new ArrayList<>();
    }

    /**
     * Purpose : Setting observer
     *
     * @param observer : parking lot observer
     */
    public void setParkingLotObserver(ParkingLotObserver observer) {
        observers.add(observer);
    }

    /**
     * Purpose : setting new capacity for lot
     *
     * @param capacity : maximum limit of parking lot
     */
    public void setCapacity(int capacity) {
        actualCapacity = capacity;
    }

    /**
     * Purpose : parks vehicle if slot is free
     *
     * @param vehicle for park
     * @throws ParkingLotException : When parking lot is full or when vehicle is not present.
     */
    public static void park(Vehicle vehicle) throws ParkingLotException {
        if (isVehicleParked(vehicle))
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.VEHICLE_IS_ALREADY_PARKED, "Vehicle Already Parked");

        if (parkingLot1.size() == actualCapacity && parkingLot2.size() == actualCapacity) {
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, "Lot is full");
        }
        if (parkingLot1.size() > parkingLot2.size()) {
            parkingLot2.add(vehicle);
        } else
            parkingLot1.add(vehicle);
        ParkingLotOwner.parkedTime(vehicle);
        checkCapacity();
        if (vehicle.getVehicleColor().equals("WHITE"))
            Police.getAllWhiteCars(GetPositionByColor(vehicle, "WHITE"));
    }

    /**
     * Purpose : check whether lot is full or not if full then passing into observers
     */
    private static void checkCapacity() {
        if (parkingLot1.size() == actualCapacity && parkingLot2.size() == actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
        }
    }

    /**
     * Purpose : Unpark vehicle from slot
     *
     * @param vehicle to unpark
     * @return boolean : true if vehicle can unpark
     * @throws ParkingLotException : When vehicle is not present
     */
    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (parkingLot1 == null || parkingLot2 == null) return false;
        for (Vehicle slot : parkingLot1) {
            if (slot.equals(vehicle)) {
                parkingLot1.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        for (Vehicle slot : parkingLot2) {
            if (slot.equals(vehicle)) {
                parkingLot2.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle Found");
    }

    /**
     * purpose : to check vehicle is parked or not
     *
     * @param vehicle : to check from each slot parked list
     * @return boolean : if parked true or else false
     */
    public static boolean isVehicleParked(Vehicle vehicle) {
        boolean isParked = false;
        for (Vehicle slot : parkingLot1)
            if (slot.equals(vehicle)) {
                isParked = true;
                break;
            }
        for (Vehicle slot : parkingLot2) {
            if (slot.equals(vehicle)) {
                isParked = true;
                break;
            }
        }
        return isParked;
    }

    /**
     * Purpose : To find spot of vehicle if vehicle is present in parking lot.
     *
     * @param vehicle to crosscheck with parked list to find parked spot
     * @return spot number of vehicle
     * @throws ParkingLotException : When vehicle is not present
     */
    public int findVehicle(Vehicle vehicle) throws ParkingLotException {
        for (Vehicle slot : parkingLot1) {
            if (slot.equals(vehicle))
                return parkingLot1.indexOf(slot) + 1;
        }
        for (Vehicle slot : parkingLot2) {
            if (slot.equals(vehicle))
                return parkingLot2.indexOf(slot) + 1;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "Vehicle is not present in parking lot");
    }

    /**
     * purpose : to find spot of vehicle with specific color
     *
     * @param vehicle : to find spot of vehicle
     * @param color   : mentioning color tobe filtered out
     * @return spot number
     * @throws ParkingLotException : when o such vehicle found
     */
    public static int GetPositionByColor(Vehicle vehicle, String color) throws ParkingLotException {
        for (Vehicle slot : parkingLot1) {
            if (slot.equals(vehicle) && slot.getVehicleColor().equals(color))
                return parkingLot1.indexOf(slot);
        }
        for (Vehicle slot : parkingLot2) {
            if (slot.equals(vehicle) && slot.getVehicleColor().equals(color))
                return parkingLot2.indexOf(slot);
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No such vehicle found");
    }

}