package com.booking.repository;

import com.booking.model.*;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class HotelRepositoryTest {
    public static final String STARS = "10";
    public static final String HOTEL_NAME = "Bangalore Inn";
    public static final int COST = 1000;
    public static final String CITY = "Bangalore";
    public static final RoomType AVAILABLE = RoomType.AVAILABLE;
    public static final RoomType NOT_AVAILABLE = RoomType.NOT_AVAILABLE;
    public static final String COMMENT1 = "Avg Hotel";
    public static final String COMMENT2 = "Avg Hotel";
    public static final String MALE = "Male";
    public static final String USER1 = "Gautam";
    public static final String USER2 = "Aditya";
    @Autowired
    private HotelRepository hotelRepository;
    private Hotel hotel = null;

    @BeforeEach
    public void setup() {
        hotel = new Hotel();
        hotel.setStars(STARS);
        hotel.setName(HOTEL_NAME);
        hotel.setCost(COST);

        //Address
        hotel.setAddress(new Address(CITY));

        //Room
        Room room1 = new Room(AVAILABLE);
        Room room2 = new Room(NOT_AVAILABLE);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        hotel.setRooms(rooms);

        //Reviews
        Review review1 = new Review(4, COMMENT1, new Users(USER1, MALE));
        Review review2 = new Review(6, COMMENT2, new Users(USER2, MALE));
        List<Review> reviews = new ArrayList<>();
        reviews.add(review1);
        reviews.add(review2);
        hotel.setReviews(reviews);

        //Amenities
        Amenities amenities = new Amenities();
        amenities.setWifiAvailable(true);
        amenities.setRestaurantAvailable(true);
        amenities.setMealIncluded(false);
        amenities.setAirConditioned(false);
        hotel.setAmenities(amenities);
    }

    @Test
    void findByName() {
        hotelRepository.save(hotel);
        Hotel actualHotel = hotelRepository.findByName(HOTEL_NAME);
        assertThat(actualHotel.getName()).isEqualTo(HOTEL_NAME);
        assertThat(actualHotel.getStars()).isEqualTo(STARS);
        assertThat(actualHotel.getCost()).isEqualTo(COST);
    }

    @Test
    void findAllByCity() {
        hotelRepository.save(hotel);
        List<Hotel> hotels = hotelRepository.findAllByCity(CITY);
        for (Hotel h : hotels) {
            assertThat(h.getName()).isEqualTo(HOTEL_NAME);
            assertThat(h.getStars()).isEqualTo(STARS);
            assertThat(h.getCost()).isEqualTo(COST);
        }
    }

    @Test
    void findAllByStar() {
        hotelRepository.save(hotel);
        List<Hotel> hotels = hotelRepository.findAllByStar(STARS);
        for (Hotel h : hotels) {
            assertThat(h.getName()).isEqualTo(HOTEL_NAME);
            assertThat(h.getStars()).isEqualTo(STARS);
            assertThat(h.getCost()).isEqualTo(COST);
        }
    }

    @Test
    void findAllByHotelByCriteria() {
        hotelRepository.save(hotel);
        List<Hotel> hotels = hotelRepository.findAllByHotelByCriteria(true, false, false, true);
        for (Hotel h : hotels) {
            assertThat(h.getName()).isEqualTo(HOTEL_NAME);
            assertThat(h.getStars()).isEqualTo(STARS);
            assertThat(h.getCost()).isEqualTo(COST);
        }
    }
}