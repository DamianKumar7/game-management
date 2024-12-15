package com.game.social.discovery.game_management.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDTO {
    private String id;
    private String slug;
    private String name;
    private String released;
    private boolean tba;
    @JsonProperty("background_image")
    private String backgroundImage;
    private int playtime;
    @JsonProperty("saturated_color")
    private String saturatedColor;
    @JsonProperty("dominant_color")
    private String dominantColor;
    private List<PlatformInfoDTO> platforms;
    private List<GenreDTO> genres;
    private Object clip;
    private List<TagDTO> tags;
    // Getters and setters
    private List<Screenshot> screenshots;
}

