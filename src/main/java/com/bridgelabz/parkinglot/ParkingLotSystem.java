package com.bridgelabz.parkinglot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Purpose : Class which has all parking lot behaviours
 *
 * @author : ALISHA BALAMWAR
 * @since : 09-11-2021
 */
public class ParkingLotSystem {
    private static List<ParkingSlot> parkingLot1;
    private static List<ParkingSlot> parkingLot2;
    private static List<ParkingLotObserver> observers;
    private static int actualCapacity;

    public ParkingLotSystem() {
        observers = new ArrayList<>();
        parkingLot1 = new ArrayList();
        parkingLot2 = new ArrayList();
    }

    /**
     * Purpose: To Set Capacity For Parking Lot
     *
     * @param capacity given as a Slot Capacity
     */
    public void setCapacity(int capacity) {
        actualCapacity = capacity;
    }

    /**
     * Purpose: To Add Observer In List
     *
     * @param observer Given Observer as a Parameter For add to in List
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        observers.add(observer);
    }

    /**
     * Purpose: To get Vehicles Parked time
     *
     * @return Date and Time For Parked Time
     */
    public static String getDateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return myDateObj.format(formatTime);
    }

    /**
     * Purpose: To Park Vehicle
     *
     * @param vehicle given vehicle as parameter For Park
     */
    public static void park(Vehicle vehicle) throws ParkingLotException {
        if (isVehicleParked(vehicle))
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.VEHICLE_IS_ALREADY_PARKED, "Vehicle Already Parked");

        if (parkingLot1.size() == actualCapacity && parkingLot2.size() == actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, "Parking Lot is Full");
        }

        ParkingSlot parkingSlot = new ParkingSlot(vehicle, getDateTime());
        if (parkingLot1.size() > parkingLot2.size()) {
            parkingLot2.add(parkingSlot);
        } else
            parkingLot1.add(parkingSlot);

        if (parkingLot1.size() - 1 == actualCapacity && parkingLot2.size() - 1 == actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, "Parking Lot is Full");
        }
    }

    /**
     * Purpose: To Check a Vehicle is Parked Or Not.
     *
     * @param vehicle reference to check that vehicle is parked or not
     * @return If slot contains Given Vehicle
     * it will return True
     */
    public static boolean isVehicleParked(Vehicle vehicle) {
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle))
                return true;
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle))
                return true;
        }
        return false;
    }

    /**
     * Purpose: To UnPark vehicle as per the demand
     *
     * @param vehicle reference to check it is unpark.
     * @return boolean value for unpark method
     * @throws if condition not matches then throws exception.
     */
    public boolean unPark(Vehicle vehicle) throws ParkingLotException {
        if (parkingLot1 == null || parkingLot2 == null) return false;
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle)) {
                parkingLot1.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle)) {
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
     * Purpose: To Find Slot Number For Parked Vehicle
     *
     * @param vehicle given Vehicle as Parameter
     * @return Vehicle Slot Number
     * @throws ParkingLotException If Vehicle Not Found Throwing Exception
     */
    public int findVehicle(Vehicle vehicle) throws ParkingLotException {
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle))
                return parkingLot1.indexOf(slot);
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle))
                return parkingLot2.indexOf(slot);
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle Found");
    }

    /**
     * Purpose To Get Park Time For Parked Vehicle
     *
     * @param vehicle given Vehicle as Parameter
     * @return Vehicle Park Time
     * @throws ParkingLotException If Vehicle Not Found Throw Exception
     */
    public static String getParkTime(Vehicle vehicle) throws ParkingLotException {
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle))
                return slot.getTime();
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle))
                return slot.getTime();
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle Found");
    }
}