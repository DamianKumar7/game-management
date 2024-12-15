package com.game.social.discovery.game_management.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StoreDTO {
    private int id;
    private String name;
    private String slug;
    private String domain;
    private int gamesCount;
    private String imageBackground;

    // Getters and setters
}