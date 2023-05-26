package com.rsreu.bestProject.enums;

import java.util.HashMap;
import java.util.Map;

public enum TagProduct {

    POST(0),

    EKO(1),

    HALAL(2);

    private final Integer id;

    private static final Map<Integer, TagProduct> map = new HashMap<>() {
        {
            put(0, POST);
            put(1, EKO);
            put(2, HALAL);
        }
    };


    TagProduct(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static TagProduct getById(Integer id) {
        return map.get(id);
    }
}