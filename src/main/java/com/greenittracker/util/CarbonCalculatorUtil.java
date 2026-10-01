package com.greenittracker.util;

public class CarbonCalculatorUtil {

    private CarbonCalculatorUtil() {
    }

    public static double calculateCarbonSaved(
            double energySaved) {

        return energySaved * 0.82;
    }
}