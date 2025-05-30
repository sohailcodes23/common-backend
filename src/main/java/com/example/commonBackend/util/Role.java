package com.example.commonBackend.util;

import lombok.Getter;

@Getter
public enum Role {
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN"),
    DELIVERY_PARTNER("DELIVERY_PARTNER");

    private final String value;

    Role(String value) {
        this.value = value;
    }


}
