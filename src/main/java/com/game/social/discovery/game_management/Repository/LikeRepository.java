package com.game.social.discovery.game_management.Repository;

import com.game.social.discovery.game_management.Model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes,Long> {
    Long countLikesByGameId(String id);
}
