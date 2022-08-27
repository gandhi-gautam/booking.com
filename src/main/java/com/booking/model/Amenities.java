package com.booking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Amenities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean isWifiAvailable;
    private boolean isRestaurantAvailable;
    private boolean isAirConditioned;
    private boolean isMealIncluded;

    public Amenities() {
    }

    public Amenities(boolean isWifiAvailable, boolean isRestaurantAvailable, boolean isAirConditioned, boolean isMealIncluded) {
        this.isWifiAvailable = isWifiAvailable;
        this.isRestaurantAvailable = isRestaurantAvailable;
        this.isAirConditioned = isAirConditioned;
        this.isMealIncluded = isMealIncluded;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getIsWifiAvailable() {
        return isWifiAvailable;
    }

    public void setWifiAvailable(boolean wifiAvailable) {
        isWifiAvailable = wifiAvailable;
    }

    public boolean getIsRestaurantAvailable() {
        return isRestaurantAvailable;
    }

    public void setRestaurantAvailable(boolean restaurantAvailable) {
        isRestaurantAvailable = restaurantAvailable;
    }

    public boolean getIsAirConditioned() {
        return isAirConditioned;
    }

    public void setAirConditioned(boolean airConditioned) {
        isAirConditioned = airConditioned;
    }

    public boolean getIsMealIncluded() {
        return isMealIncluded;
    }

    public void setMealIncluded(boolean mealIncluded) {
        isMealIncluded = mealIncluded;
    }
}
