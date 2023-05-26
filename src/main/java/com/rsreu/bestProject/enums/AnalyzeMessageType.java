package com.rsreu.bestProject.enums;

import java.util.HashMap;
import java.util.Map;

public enum AnalyzeMessageType {
    ADD(0),
    UPDATE(1),

    REMOVE(2);

    private final Integer id;

    private static final Map<Integer, AnalyzeMessageType> map = new HashMap<>(){{
        put(0, ADD);
        put(1, UPDATE);
        put(2, REMOVE);
    }
    };


    AnalyzeMessageType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static AnalyzeMessageType getById(Integer id) {
        return map.get(id);
    }
}
