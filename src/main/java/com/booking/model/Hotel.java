package com.booking.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String stars;

    @Column(unique = true)
    private String name;

    private int cost;

    @JoinColumn(name = "address_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private List<Room> rooms;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private List<Review> reviews;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "amaneties_id")
    private Amenities amenities;

    public Hotel() {

    }

    public Hotel(String stars, String name, int cost, Address address, List<Room> rooms, List<Review> reviews, Amenities amenities) {
        this.stars = stars;
        this.name = name;
        this.cost = cost;
        this.address = address;
        this.rooms = rooms;
        this.reviews = reviews;
        this.amenities = amenities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public Amenities getAmenities() {
        return amenities;
    }

    public void setAmenities(Amenities amenities) {
        this.amenities = amenities;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
