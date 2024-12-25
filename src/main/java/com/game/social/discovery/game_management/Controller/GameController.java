package com.game.social.discovery.game_management.Controller;

import ch.qos.logback.core.util.StringUtil;
import com.game.social.discovery.game_management.DTO.GameDTO;
import com.game.social.discovery.game_management.DTO.GameResponseDTO;
import com.game.social.discovery.game_management.Service.GameCacheService;
import com.game.social.discovery.game_management.Service.GameService;
import com.game.social.discovery.game_management.Service.LikeService;
import com.game.social.discovery.game_management.Service.RatingService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    GameCacheService gameCacheService;

    @Autowired
    LikeService likeService;

    @Autowired
    RatingService ratingService;

    @GetMapping("/")
    public ResponseEntity<?> getAllGames(@RequestParam("pageId")String pageId, @RequestParam(name = "search", required = false )String searchKeyword, @RequestParam(value = "genre", required = false)String genre){

        if(StringUtil.isNullOrEmpty(searchKeyword) && StringUtil.isNullOrEmpty(genre)){
            GameResponseDTO gameResponseDTO = gameService.getAllGames(pageId);
            if(gameResponseDTO.getSuccess()){
                return new ResponseEntity<>(gameResponseDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(gameResponseDTO.getErrorMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecificGameData(@PathVariable String id){
        //Step 1: Find whether the game exists in our cache db
        //Step 2: If the game does not exist in our cache db, then we need to hit the RAWG API
        GameDTO gameDTO = gameCacheService.getGameDetailsFromCache(id);
        if(gameDTO!= null){
            return new ResponseEntity<>(gameDTO,HttpStatus.OK);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<?> likeGame(@PathVariable(name = "id") String gameId, @RequestParam String userId){
        int success = likeService.likeGame(gameId,userId);
        if (success == 1) {
            return ResponseEntity.ok("Game liked successfully!");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to like the game. Please try again later.");
        }
    }

    @PostMapping("rate/{id}")
    public ResponseEntity<?> rateGame(@PathVariable(name = "id")String gameId, @RequestParam(name = "userId") String userId, @RequestParam(name = "rating")BigDecimal rating){
        int success = ratingService.rateGame(gameId,userId,rating);
        if (success == 1) {
            return ResponseEntity.ok("Game rated successfully!");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to rate the game. Please try again later.");
        }
    }
}
