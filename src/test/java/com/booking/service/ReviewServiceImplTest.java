package com.booking.service;

import com.booking.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReviewServiceImplTest {

    @MockBean
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @Test
    void deleteReview() {
        reviewService.deleteReview(1);
        verify(reviewRepository, times(1)).deleteById(1L);
    }

    @Test
    void userExists() {
        when(reviewRepository.existsById(1l)).thenReturn(false);
        assertFalse(reviewService.userExists(1l));
    }
}