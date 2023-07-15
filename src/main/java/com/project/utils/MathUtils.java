package com.project.utils;

public class MathUtils {
    public static double roundToTwoDecimals(double value){
        return Math.round(value*100.0)/100.0;
    }
}
