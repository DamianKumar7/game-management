package com.game.social.discovery.game_management.Service;

import com.game.social.discovery.game_management.Model.Likes;
import com.game.social.discovery.game_management.Repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {
    //this class exists to fetch the likes from the particular game id
    @Autowired
    LikeRepository likeRepository;

    public Long getLikesForGameId(String id) {
        Long likeCount = likeRepository.countLikesByGameId(id);
        return likeCount;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Integer likeGame(String id, String userId) {
        Likes like = new Likes();
        like.setGameId(id);
        like.setUserId(userId);
        like.setTimestamp(System.currentTimeMillis());
        int success = 0;
        try{
            likeRepository.save(like);
            success = 1;
        }catch(Exception e){
            success = -1;
        }
        return success;
    }
}