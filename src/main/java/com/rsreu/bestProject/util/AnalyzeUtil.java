package com.rsreu.bestProject.util;

import com.rsreu.bestProject.dto.analyse.AnalyzeMessage;
import com.rsreu.bestProject.enums.AnalyzeMessageType;

public class AnalyzeUtil {

    public static <T> AnalyzeMessage<T> getMessage(T object, AnalyzeMessageType type) {
        return new AnalyzeMessage<>(type.getId(), object);
    }
}
