package com.aditya.trucker.service;

import com.aditya.trucker.model.Review;
import com.aditya.trucker.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;

    public List<Review> getAllReviews() {
        return reviewRepo.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepo.findById(id);
    }

    public Review addReview(Review review) {
        return reviewRepo.save(review);
    }

    public Review updateReview(Long id, Review updatedReview) {
        return reviewRepo.findById(id).map(review -> {
            review.setRating(updatedReview.getRating());
            review.setComment(updatedReview.getComment());
            review.setDate(updatedReview.getDate());
            review.setFoodTruck(updatedReview.getFoodTruck());
            review.setCustomer(updatedReview.getCustomer());
            return reviewRepo.save(review);
        }).orElseGet(() -> {
            updatedReview.setId(id);
            return reviewRepo.save(updatedReview);
        });
    }

    public void deleteReview(Long id) {
        reviewRepo.deleteById(id);
    }
}
