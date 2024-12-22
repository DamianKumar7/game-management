package com.game.social.discovery.game_management.Service;

import com.game.social.discovery.game_management.Repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    //this class exists to fetch the likes from the particular game id
    @Autowired
    LikeRepository likeRepository;

    public Long getLikesForGameId(String id) {
        Long likeCount = likeRepository.countLikesByGameId(id);
        return likeCount;
    }
}