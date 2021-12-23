package com.sbrf.reboot.calculator;

public class Calculator {
    public int getAddition(int x, int y) {
        return x + y;
    }

    public int getSubtraction(int x, int y) {
        return x - y;
    }

    public int getMultiplication(int x, int y) {
        return x * y;
    }

    public int getDivision(int x, int y) {
        return x / y;
    }

    public double getSin(double degree) {
        double radians = Math.toRadians(degree);
        return Math.sin(radians);
    }

    public double getCos(double degree) {
        double radians = Math.toRadians(degree);
        return Math.cos(radians);
    }

    public double getTan(double degree) {
        double radians = Math.toRadians(degree);
        return Math.tan(radians);
    }
}