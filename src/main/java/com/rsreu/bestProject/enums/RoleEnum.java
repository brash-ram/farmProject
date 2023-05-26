package com.rsreu.bestProject.enums;

import java.util.HashMap;
import java.util.Map;

public enum RoleEnum {
    ADMIN(0),
    USER(1);

    private final Integer id;

    private static final Map<Integer, RoleEnum> map = new HashMap<>(){{
            put(0, ADMIN);
            put(1, USER);
        }
    };


    RoleEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static RoleEnum getById(Integer id) {
        return map.get(id);
    }
}
