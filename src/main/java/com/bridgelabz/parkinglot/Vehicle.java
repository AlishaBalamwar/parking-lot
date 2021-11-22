package com.bridgelabz.parkinglot;

import java.util.Objects;

public class Vehicle {
    public static boolean isDriverHandicapped;
    private final String vehicleNumber;
    private final String vehicleName;
    private final String vehicleColor;
    private final Size size;

    public enum Size {LARGE, SMALL, MEDIUM}

    public Vehicle(String vehicleNumber, Size size,String vehicleName, boolean isDriverHandicapped,String vehicleColor) {
        this.vehicleNumber = vehicleNumber;
        this.size = size;
        this.vehicleColor = vehicleColor;
        this.vehicleName = vehicleName;
        this.isDriverHandicapped = isDriverHandicapped;
    }
    public Size getSize() {
        return size;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vehicleNumber, vehicle.vehicleNumber) && Objects.equals(vehicleName, vehicle.vehicleName) && Objects.equals(vehicleColor, vehicle.vehicleColor) && size == vehicle.size;
    }
}
