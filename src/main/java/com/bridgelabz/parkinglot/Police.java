package com.bridgelabz.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Purpose : To list All spot of white cars
 *
 * @author : Alisha Balamwar
 * @since : 09-11-2021
 */

public class Police {

    static ArrayList whiteCars = new ArrayList();
    static ArrayList BMWCars = new ArrayList();
    static ArrayList toyotaBlueCars = new ArrayList();


    /**
     * Purpose : to add parking spot into list
     *
     * @param spotNo parking spot number to save into list
     */
    public static void getAllWhiteCars(int spotNo) {
        whiteCars.add(spotNo);
    }

    public static boolean whiteCarsContains(int spotNumber) {
        return whiteCars.contains(spotNumber);
    }

    public static void listOfBMW(Integer spotNumber) {
        BMWCars.add(spotNumber);
    }

    public static boolean getAllBMWCars(int spotNumber) {
        return BMWCars.contains(spotNumber);
    }


    public static void getAllToyotaBlueCars(Vehicle vehicle) {
        toyotaBlueCars.add(vehicle);
    }

    public static boolean checkBlueToyota(Vehicle vehicle) {
        return toyotaBlueCars.contains(vehicle);
    }

}