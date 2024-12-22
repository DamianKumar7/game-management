package com.game.social.discovery.game_management.Service;

import com.game.social.discovery.game_management.Repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public BigDecimal calculateAverageRatingForGameId(String id) {
        // Get the total number of ratings for the given gameId
        Long totalRatings = ratingRepository.countByGameId(id);

        // Get the sum of all ratings for the given gameId
        BigDecimal sumOfRatings = ratingRepository.sumRatingByGameId(id);

        // If there are no ratings, return BigDecimal.ZERO (or null, depending on your preference)
        if (totalRatings == 0 || sumOfRatings == null) {
            return BigDecimal.ZERO;
        }

        // Calculate the average
        return sumOfRatings.divide(BigDecimal.valueOf(totalRatings), 2, BigDecimal.ROUND_HALF_UP);
    }
}
