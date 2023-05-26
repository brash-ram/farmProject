package com.rsreu.bestProject.enums;

import java.util.HashMap;
import java.util.Map;

public enum DeliveryType {

    PICKUP(0),

    FARMER_DELIVERY(1),

    OUR_DELIVERY(2);

    private final Integer id;

    private static final Map<Integer, DeliveryType> map = new HashMap<>(){{
        put(0, PICKUP);
        put(1, FARMER_DELIVERY);
        put(2, OUR_DELIVERY);
    }
    };


    DeliveryType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static DeliveryType getById(Integer id) {
        return map.get(id);
    }
}
