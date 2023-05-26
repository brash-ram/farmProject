package com.rsreu.bestProject.enums;

import java.util.HashMap;
import java.util.Map;

public enum Unit {
    KG(0),
    PIECES(1);

    private final Integer id;

    private static final Map<Integer, Unit> map = new HashMap<>(){{
        put(0, KG);
        put(1, PIECES);
    }};


    Unit(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static Unit getById(Integer id) {
        return map.get(id);
    }
}
