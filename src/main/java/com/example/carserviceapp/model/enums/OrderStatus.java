package com.example.carserviceapp.model.enums;

public enum OrderStatus {
    ACCEPTED("Accepted"),
    DURING("During"),
    UNCOMPLETED("Uncompleted"),
    COMPLETED("Completed");


    private String value;

    OrderStatus(String value) {
        this.value = value;
    }
}
