package com.company.networkmovers.modules.booking.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ScheduleType {
    INSTANT("instant"),
    SCHEDULED("scheduled");

    private final String value;

    ScheduleType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ScheduleType fromValue(String value) {
        if (value == null) {
            return null;
        }
        for (ScheduleType type : ScheduleType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        try {
            return ScheduleType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown schedule type: " + value);
        }
    }
}
