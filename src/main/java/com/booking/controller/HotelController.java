package com.booking.controller;

import com.booking.exception.EmptyFieldException;
import com.booking.exception.ResourceAlreadyExistsException;
import com.booking.exception.ResourceNotFoundException;
import com.booking.model.Hotel;
import com.booking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    /**
     * This method saves hotel data in the database
     *
     * @param hotel
     * @return
     */
    @PostMapping()
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
        Hotel existingHotel = null;
        if (hotel.getName() != null) {
            existingHotel = hotelService.findByName(hotel.getName());
        } else {
            throw new EmptyFieldException("Hotel name not provided ");
        }
        if (existingHotel == null) {
            hotel = hotelService.saveHotel(hotel);
        } else {
            throw new ResourceAlreadyExistsException("Hotel already exists");
        }
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    /**
     * This method updated the hotel data in the database
     *
     * @param hotel
     * @return
     */
    @PutMapping()
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) {
        Hotel existingHotel = null;
        if (hotel.getName() != null) {
            existingHotel = hotelService.findByName(hotel.getName());
        }
        if (existingHotel != null) {
            hotel = hotelService.updateHotel(hotel, existingHotel);
        } else {
            throw new ResourceNotFoundException("Hotel not found");
        }
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    /**
     * This method deletes hotel data from database
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable long id) {
        if (!hotelService.hotelExists(id)) {
            throw new ResourceNotFoundException("Hotel with id: " + id + " not found");
        }
        hotelService.deleteHotel(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
