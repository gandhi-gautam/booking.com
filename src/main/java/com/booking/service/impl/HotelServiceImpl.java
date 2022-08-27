package com.booking.service.impl;

import com.booking.model.Hotel;
import com.booking.model.Review;
import com.booking.repository.HotelRepository;
import com.booking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    /**
     * This method saves hotel in the database, after calculation of the avg stars
     *
     * @param hotel
     * @return
     */
    @Override
    public Hotel saveHotel(Hotel hotel) {
        double stars = 0;
        if (hotel != null && hotel.getName() != null) {
            Hotel actualHotel = findByName(hotel.getName());
            if (actualHotel != null) {
                stars = calculateAvgStars(actualHotel, Double.parseDouble(actualHotel.getStars()));
            }
            stars = calculateAvgStars(hotel, stars);
            hotel.setStars(String.valueOf(stars));
            return hotelRepository.save(hotel);

        }
        return null;
    }

    /**
     * This methods updated the hotel data in the database
     *
     * @param hotel
     * @return
     */
    @Override
    public Hotel updateHotel(Hotel hotel, Hotel existingHotel) {
        existingHotel.setStars(hotel.getStars());
        existingHotel.setAddress(hotel.getAddress());
        existingHotel.setAmenities(hotel.getAmenities());
        existingHotel.setReviews(hotel.getReviews());
        existingHotel.setRooms(hotel.getRooms());
        return saveHotel(existingHotel);
    }

    /**
     * This method deletes hotel data based on id
     *
     * @param id
     */
    @Override
    public void deleteHotel(long id) {
        hotelRepository.deleteById(id);
    }

    /**
     * This method sorts all hotel by cost
     *
     * @param direction
     * @return
     */
    @Override
    public List<Hotel> sortByCost(String direction) {
        Sort sort = null;
        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(Sort.Direction.DESC, "cost");
        } else {
            sort = Sort.by(Sort.Direction.ASC, "cost");
        }
        return hotelRepository.findAll(sort);
    }

    /**
     * This method sorts all hotel by stars/review
     *
     * @param direction
     * @return
     */
    @Override
    public List<Hotel> sortByStars(String direction) {
        Sort sort = null;
        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(Sort.Direction.DESC, "stars");
        } else {
            sort = Sort.by(Sort.Direction.ASC, "stars");
        }
        return hotelRepository.findAll(sort);
    }

    /**
     * This method find the hotel by name
     *
     * @param name
     * @return
     */
    @Override
    public Hotel findByName(String name) {
        return hotelRepository.findByName(name);
    }

    /**
     * This method check if hotel exists in database
     *
     * @param id
     * @return
     */
    @Override
    public boolean hotelExists(long id) {
        if (!hotelRepository.existsById(id)) {
            return false;
        }
        return true;
    }

    /**
     * This method calculate average stars received by all the users
     *
     * @param hotel
     * @param stars
     * @return
     */
    private double calculateAvgStars(Hotel hotel, double stars) {
        double avgStars = 0;
        int count = 0;
        if (stars > 0) {
            count++;
            avgStars = stars;
        }

        for (Review review : hotel.getReviews()) {
            avgStars += review.getStars();
            count++;
        }
        stars = avgStars / count;
        return stars;
    }
}
