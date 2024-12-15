package com.game.social.discovery.game_management.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameResponseDTO {
    private int count;
    private String next;
    private String previous;
    private List<GameDTO> results;
    private Boolean success;
    private String errorMessage;
    // Getters and setters
}






