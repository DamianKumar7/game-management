package com.game.social.discovery.game_management.Controller;

import ch.qos.logback.core.util.StringUtil;
import com.game.social.discovery.game_management.DTO.GameResponseDTO;
import com.game.social.discovery.game_management.Service.GameCacheService;
import com.game.social.discovery.game_management.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    GameCacheService gameCacheService;

    @GetMapping("/")
    public ResponseEntity<?> getAllGames(@RequestParam("pageId")String pageId, @RequestParam("search")String searchKeyword, @RequestParam("genre")String genre){

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
    public ResponseEntity<?> getSpecificGameData(@PathVariable("id")String id){
        //Step 1: Find whether the game exists in our cache db
        //Step 2: If the game does not exist in our cache db, then we need to hit the RAWG API

    }
}
