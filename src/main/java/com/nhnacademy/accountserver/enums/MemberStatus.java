package com.nhnacademy.accountserver.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MemberStatus {
    ACTIVE, SLEEPER, QUIT;

    @JsonCreator
    public static MemberStatus fromString(String str){
        for (MemberStatus value : MemberStatus.values()) {
            if (value.name().equalsIgnoreCase(str)) {
                return value;
            }
        }
        return SLEEPER;
    }

    @JsonValue
    public String toJson(){
        return this.name().toLowerCase();
    }
}
