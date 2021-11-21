package com.bridgelabz.parkinglot;

public class ParkingLotException extends Exception {
    public ExceptionType exceptionType;

    public enum ExceptionType {PARKING_LOT_IS_FULL, NO_SUCH_VEHICLE, VEHICLE_IS_ALREADY_PARKED}

    public ParkingLotException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }
}
