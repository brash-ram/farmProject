package com.rsreu.bestProject.util;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DateUtils {

    public static OffsetDateTime parse(Long time) {
        return OffsetDateTime.of(LocalDateTime.ofEpochSecond(time, 0, ZoneOffset.UTC), ZoneOffset.UTC);
    }
}
