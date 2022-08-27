package com.booking.repository;

import com.booking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    /**
     * This method find the hotel based on name from the database
     *
     * @param name
     * @return
     */
    @Query("from Hotel where name = :name")
    Hotel findByName(String name);

    /**
     * This method find all the hotel by city
     *
     * @param city
     * @return
     */
    @Query("SELECT h from Hotel h where h.address.city like %?1%")
    List<Hotel> findAllByCity(String city);

    /**
     * This method finds all the hotels based on stars
     *
     * @param star
     * @return
     */
    @Query("SELECT h from Hotel h where h.stars like %?1%")
    List<Hotel> findAllByStar(String star);

    /**
     * This method finds all hotels based on amenities available
     *
     * @param isWifiAvailable
     * @param isAirConditioned
     * @param isMealIncluded
     * @param isRestaurantAvailable
     * @return
     */
    @Query("Select h from Hotel h where h.amenities.isWifiAvailable =:isWifiAvailable " + "or h.amenities.isAirConditioned=:isAirConditioned or h.amenities.isMealIncluded =:isMealIncluded " + "or h.amenities.isRestaurantAvailable =:isRestaurantAvailable")
    List<Hotel> findAllByHotelByCriteria(boolean isWifiAvailable, boolean isAirConditioned, boolean isMealIncluded, boolean isRestaurantAvailable);
}
