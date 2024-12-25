package com.game.social.discovery.game_management.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "games")
public class GameData {
    @Id
    private String id;

    @Column(nullable = true, unique = true)
    private String slug;

    @Column(nullable = true)
    private String name;

    private String released;

    private boolean tba;

    @Column(name = "background_image")
    private String backgroundImage;

    private int playtime;

    @Column(name = "saturated_color")
    private String saturatedColor;

    @Column(name = "dominant_color")
    private String dominantColor;

    @Column(columnDefinition = "TEXT")
    private String platforms;

    @Column(columnDefinition = "TEXT")
    private String genres;

    private String clip;

    @Column(columnDefinition = "TEXT")
    private String tags;

    @Column(columnDefinition = "TEXT")
    private String screenshots;

    private Long likes;

    private BigDecimal rating;
}
