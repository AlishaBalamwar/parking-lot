package com.bridgelabz.parkinglot;

public class ParkingSlot {
    private final Vehicle vehicle;
    private final String time;

    public ParkingSlot(Vehicle vehicle, String time) {
        this.vehicle = vehicle;
        this.time = time;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot parkingSlot1 = (ParkingSlot) o;
        return vehicle.equals(parkingSlot1.vehicle) && time.equals(parkingSlot1.time);
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "vehicle=" + vehicle +
                ", time='" + time + '\'' +
                '}';
    }
}