package com.bridgelabz.parkinglot;

public class Vehicle {
    public static boolean isDriverHandicapped;
    private final String vehicleNumber;
    private final String vehicleName;
    private final String vehicleColor;

    public Vehicle(String vehicleNumber, String vehicleName, boolean isDriverHandicapped,String vehicleColor) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleColor = vehicleColor;
        this.vehicleName = vehicleName;
        this.isDriverHandicapped = isDriverHandicapped;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public boolean isDriverHandicapped() {
        return isDriverHandicapped;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }
}
