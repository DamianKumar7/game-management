package com.game.social.discovery.game_management.Controller;

import com.game.social.discovery.game_management.DTO.GameResponseDTO;
import com.game.social.discovery.game_management.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping("/")
    public ResponseEntity<?> getAllGames(@RequestParam("pageId")String pageId){
        GameResponseDTO gameResponseDTO = gameService.getAllGames(pageId);
        if(gameResponseDTO.getSuccess()){
            return new ResponseEntity<>(gameResponseDTO, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(gameResponseDTO.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
