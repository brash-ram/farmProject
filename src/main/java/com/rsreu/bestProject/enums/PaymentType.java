package com.rsreu.bestProject.enums;

import java.util.HashMap;
import java.util.Map;

public enum PaymentType {
    CARD(0),
    CASH(1),

    SBP(2);

    private final Integer id;

    private static final Map<Integer, PaymentType> map = new HashMap<>(){{
        put(0, CARD);
        put(1, CASH);
        put(2, SBP);
    }
    };


    PaymentType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static PaymentType getById(Integer id) {
        return map.get(id);
    }
}
