package com.example.carserviceapp.model.enums;

public enum PaymentStatus {
    PAID("Paid"),
    UNPAID("Unpaid");

    private String value;

    PaymentStatus(String value) {
        this.value = value;
    }
}
