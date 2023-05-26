package com.rsreu.bestProject.util;

import java.util.List;

public class RatingUtil {

    public static Double getAverage(List<Integer> rating) {
        Double sum = 0D;
        for (Integer value : rating) {
            sum += value;
        }
        return sum / rating.size();
    }
}
