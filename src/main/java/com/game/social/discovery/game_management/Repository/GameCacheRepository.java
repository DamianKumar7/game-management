package com.game.social.discovery.game_management.Repository;

import com.game.social.discovery.game_management.Model.GameData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameCacheRepository extends JpaRepository<GameData,String> {
    GameData getGameFromId(String id);
}
