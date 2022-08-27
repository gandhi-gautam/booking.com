package com.booking.controller;

import com.booking.model.Hotel;
import com.booking.repository.AmenitiesRepository;
import com.booking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private AmenitiesRepository amenitiesRepository;

    /**
     * This method contains the search functionality based on various keywords
     *
     * @param city
     * @param stars
     * @param isWifiAvailable
     * @param isRestaurantAvailable
     * @param isAirConditioned
     * @param isMealIncluded
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<Hotel>> search(@RequestParam(required = false) String city, @RequestParam(required = false) String stars, @RequestParam(required = false, defaultValue = "false") boolean isWifiAvailable, @RequestParam(required = false, defaultValue = "false") boolean isRestaurantAvailable, @RequestParam(required = false, defaultValue = "false") boolean isAirConditioned, @RequestParam(required = false, defaultValue = "false") boolean isMealIncluded) {

        List<Hotel> hotels = new ArrayList<>();
        if (city != null) {
            hotels.addAll(hotelRepository.findAllByCity(city));
        }

        if (stars != null) {
            hotels.addAll(hotelRepository.findAllByStar(stars));
        }

        hotels.addAll(hotelRepository.findAllByHotelByCriteria(isWifiAvailable, isAirConditioned, isMealIncluded, isRestaurantAvailable));

        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }
}
