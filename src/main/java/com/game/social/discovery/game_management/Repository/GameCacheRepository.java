package com.game.social.discovery.game_management.Repository;

import com.game.social.discovery.game_management.Model.GameData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameCacheRepository extends JpaRepository<GameData,String> {
    Optional<GameData> findById(String id);
}
