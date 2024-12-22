package com.game.social.discovery.game_management.Repository;

import com.game.social.discovery.game_management.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    // Query to get the total number of ratings for a particular gameId
    @Query("SELECT COUNT(r) FROM Rating r WHERE r.gameId = :gameId")
    Long countByGameId(String gameId);

    // Query to get the sum of all ratings for a particular gameId
    @Query("SELECT SUM(r.rating) FROM Rating r WHERE r.gameId = :gameId")
    BigDecimal sumRatingByGameId(String gameId);
}
