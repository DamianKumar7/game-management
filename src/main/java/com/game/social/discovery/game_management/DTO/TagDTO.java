package com.game.social.discovery.game_management.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {
    private Long id;
    private String name;
    private String slug;
    private String language;
    private int gamesCount;
    private String imageBackground;

    // Getters and setters
}