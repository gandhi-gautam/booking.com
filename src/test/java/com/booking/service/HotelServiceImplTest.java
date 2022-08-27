package com.booking.service;

import com.booking.model.*;
import com.booking.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class HotelServiceImplTest {
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
    private HotelService hotelService;

    @MockBean
    private HotelRepository hotelRepository;

    private Hotel hotel = null;

    @BeforeEach
    void setup() {
        hotel = new Hotel();
        hotel.setId(1);
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
    void saveHotel() {
        when(hotelRepository.save(hotel)).thenReturn(hotel);
        assertEquals(hotel, hotelService.saveHotel(hotel));
    }

    @Test
    void updateHotel() {
        when(hotelRepository.save(hotel)).thenReturn(hotel);
        assertEquals(hotel, hotelService.updateHotel(hotel, hotel));
    }

    @Test
    void deleteHotel() {
        hotelService.deleteHotel(1);
        verify(hotelRepository, times(1)).deleteById(1L);
    }

    @Test
    void sortByCost() {
        hotelService.sortByCost("asc");
        verify(hotelRepository, times(1)).findAll(Sort.by(Sort.Direction.ASC, "cost"));
    }

    @Test
    void sortByStars() {
        hotelService.sortByStars("asc");
        verify(hotelRepository, times(1)).findAll(Sort.by(Sort.Direction.ASC, "stars"));
    }

    @Test
    void findByName() {
        hotelService.findByName(HOTEL_NAME);
        verify(hotelRepository, times(1)).findByName(HOTEL_NAME);
    }

    @Test
    void hotelExists() {
        when(hotelRepository.existsById(1l)).thenReturn(false);
        assertFalse(hotelService.hotelExists(1l));
    }
}