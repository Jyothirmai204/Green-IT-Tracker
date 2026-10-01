package com.greenittracker.util;

public class GreenScoreCalculator {

    private GreenScoreCalculator() {
    }

    public static int calculateGreenScore(
            int resourceScore,
            int storageScore,
            int vmScore,
            int carbonScore,
            int paperScore) {

        return (int) (
                resourceScore * 0.30 +
                storageScore * 0.20 +
                vmScore * 0.20 +
                carbonScore * 0.15 +
                paperScore * 0.15
        );
    }
}