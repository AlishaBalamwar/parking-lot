package com.bridgelabz.parkinglot;

import java.util.ArrayList;

/**
 * Purpose : To list All spot of white cars
 *
 * @author : Alisha Balamwar
 * @since : 09-11-2021
 */

public class Police {

    static ArrayList whiteCars = new ArrayList();

    /**
     * Purpose : to add parking spot into list
     *
     * @param spotNo parking spot number to save into list
     */
    public static void getAllWhiteCars(int spotNo) {
        whiteCars.add(spotNo);
    }
}
