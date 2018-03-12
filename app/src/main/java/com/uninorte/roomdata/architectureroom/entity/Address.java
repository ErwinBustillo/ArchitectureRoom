package com.uninorte.roomdata.architectureroom.entity;

/**
 * Created by erwin on 3/12/2018.
 */

public class Address {
    public String city;
    public String neighborhood;

    public Address(String city, String neighborhood) {
        this.city = city;
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
