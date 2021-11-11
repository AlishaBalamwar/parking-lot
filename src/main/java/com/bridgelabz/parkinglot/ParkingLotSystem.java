package com.bridgelabz.parkinglot;

/**
 * Purpose : Class which has all parking lot behaviours
 *
 * @author : ALISHA BALAMWAR
 * @since : 09-11-2021
 */
public class ParkingLotSystem {

    private Object vehicle;

    public ParkingLotSystem() {
    }

    /**
     * Purpose: Parks the vehicle when slot is has vacant spaces
     *
     * @param vehicle : reference type of object
     * @throws ParkingLotException
     */
    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException("Parking Lot is Full");
        this.vehicle = vehicle;
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
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
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
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }
}
