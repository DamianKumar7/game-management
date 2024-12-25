package com.game.social.discovery.game_management.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="ratings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the rating

    @Column(nullable = false)
    private String gameId; // The ID of the game being rated (foreign key to a Game entity)

    @Column(nullable = false)
    private BigDecimal rating; // The rating given (assumed to be a decimal value)

    @Column(nullable = false)
    private String userId; // The ID of the user who gave the rating (optional, if you track users)

    @Column(nullable = true)
    private String username; // Optional: username of the person giving the rating (if you want to store it)

    @Column(nullable = false)
    private Long timestamp; // Timestamp of when the rating was given

}
