package com.rsreu.bestProject.util;

import java.util.Random;

public class CodeUtil {

    public static String getCode() {
        Random rnd = new Random();
        int randomInt = rnd.nextInt(900000) + 100000;
        return Integer.toString(randomInt);
    }
}
