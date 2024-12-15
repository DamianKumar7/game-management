package com.game.social.discovery.game_management.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlatformInfoDTO {
    private PlatformDTO platform;
    private String releasedAt;
    private RequirementsDTO requirementsEn;
    private RequirementsDTO requirementsRu;

    // Getters and setters
}
