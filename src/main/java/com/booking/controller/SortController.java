package com.booking.controller;

import com.booking.model.Hotel;
import com.booking.service.HotelService;
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
@RequestMapping("/sort")
public class SortController {
    @Autowired
    private HotelService hotelService;

    /**
     * This method sort the hotel data based on sorting keywords
     *
     * @param cost
     * @param stars
     * @param direction
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<Hotel>> sort(@RequestParam(required = false, defaultValue = "false") boolean cost, @RequestParam(required = false, defaultValue = "false") boolean stars, @RequestParam(required = false, defaultValue = "asc") String direction) {
        List<Hotel> hotels = new ArrayList<>();
        if (cost) {
            hotels.addAll(hotelService.sortByCost(direction));
        } else {
            hotels.addAll(hotelService.sortByStars(direction));
        }

        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }
}
