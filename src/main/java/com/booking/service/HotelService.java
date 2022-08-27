package com.booking.service;

import com.booking.model.Hotel;

import java.util.List;

public interface HotelService {
    public Hotel saveHotel(Hotel hotel);

    public Hotel updateHotel(Hotel hotel, Hotel existingHotel);

    public void deleteHotel(long id);

    public List<Hotel> sortByCost(String direction);

    public List<Hotel> sortByStars(String direction);

    public Hotel findByName(String name);

    public boolean hotelExists(long id);
}
