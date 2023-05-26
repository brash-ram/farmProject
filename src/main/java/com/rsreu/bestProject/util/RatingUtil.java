package com.rsreu.bestProject.util;

import java.util.List;

public class RatingUtil {

    public static Double getAverage(List<Integer> rating) {
        if (rating == null || rating.size() < 2) {
            return 0d;
        }

        Double sum = 0D;
        for (Integer value : rating) {
            sum += value;
        }
        return sum / rating.size();
    }
}
