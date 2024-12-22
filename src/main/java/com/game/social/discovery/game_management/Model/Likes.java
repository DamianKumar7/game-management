package com.game.social.discovery.game_management.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "likes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String gameId; // The ID of the game being rated (foreign key to a Game entity)

    @Column(nullable = false)
    private Long userId; // The ID of the user who gave the rating (optional, if you track users)

    @Column(nullable = false)
    private String username; // Optional: username of the person giving the rating (if you want to store it)

    @Column(nullable = false)
    private Long timestamp;
}
