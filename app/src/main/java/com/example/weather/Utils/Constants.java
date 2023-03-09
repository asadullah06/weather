package com.example.weather.Utils;

public enum Constants {
    API_KEY("5756ff5c335b1b8607331f460a34d753");

    private String constant;

    public String getValue() {
        return constant;
    }

    Constants (String constant){
        this.constant = constant;
    }
}
