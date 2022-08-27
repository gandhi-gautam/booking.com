package com.booking.service.impl;

import com.booking.repository.ReviewRepository;
import com.booking.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * This methods delete review based on id from the database
     *
     * @param id
     */
    @Override
    public void deleteReview(long id) {

        reviewRepository.deleteById(id);
    }

    /**
     * This method check if review exists in the database
     *
     * @param id
     * @return
     */
    @Override
    public boolean userExists(long id) {
        if (!reviewRepository.existsById(id)) {
            return false;
        }
        return true;
    }
}
