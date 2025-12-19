package com.bookmyshow.demo.dtos;

import lombok.Data;

@Data
public class TheatreDTO {

    private String name;
    private String location;
    private int capacity;
    private String screenType;

    public Object getTheatreLocation() {
        return location;
    }

    public Object getTheatreName() {
        return name;
    }

    public Object getTheatreCapacity() {
        return capacity;
    }

    public Object getTheatreScreenType() {
        return screenType;
    }
}
