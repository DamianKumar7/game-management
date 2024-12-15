package com.game.social.discovery.game_management.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlatformDTO {
    private int id;
    private String name;
    private String slug;
    private String image;
    private Integer yearEnd;
    private Integer yearStart;
    private int gamesCount;
    private String imageBackground;
}
