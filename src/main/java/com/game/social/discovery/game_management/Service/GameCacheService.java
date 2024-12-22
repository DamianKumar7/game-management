package com.game.social.discovery.game_management.Service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.social.discovery.game_management.DTO.*;
import com.game.social.discovery.game_management.Model.GameData;
import com.game.social.discovery.game_management.Repository.GameCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class GameCacheService {

    @Autowired
    GameCacheRepository gameCacheRepository;

    @Autowired
    LikeService likeService;

    @Autowired
    RatingService ratingService;

    @Autowired
    GameService gameService;

    private GameDTO gameDTO;
    private Long likeCount;
    private BigDecimal avgRating;

    public GameDTO getGameDetailsFromCache(String id){
        Optional<GameData> gameData = gameCacheRepository.findById(id);
        if(!gameData.isPresent()){
            likeCount = 0L;
            avgRating = BigDecimal.valueOf(0.0);
            //get Data from the RAWG API
            gameDTO = gameService.getGameDataById(id);
            gameDTO.setLikes(likeCount);
            gameDTO.setRating(avgRating);
            //set the data in the DB
            int success = setDataInCacheDB(gameDTO);
        }
        else{
            likeCount = likeService.getLikesForGameId(id);
            avgRating = ratingService.calculateAverageRatingForGameId(id);
            convertGameDataToResponoseData(gameData.get(),likeCount,avgRating);
        }

        return gameDTO;
    }

    private void convertGameDataToResponoseData(GameData gameData,Long likeCount,BigDecimal avgRating) {
        //todo
        gameDTO = convertToGameDTO(gameData);
        gameDTO.setLikes(likeCount);
        gameDTO.setRating(avgRating);
    }

    private Integer setDataInCacheDB(GameDTO gameDTO){
        //convert GameDTO into entity
        GameData gameData = convertToEntity(gameDTO);
        try{
            gameCacheRepository.save(gameData);
            return 1;
        } catch (Exception e) {
            return 0;
        }

    }

    private GameData convertToEntity(GameDTO gameDTO){
        GameData gameData = new GameData();
        gameData.setId(gameDTO.getId());
        gameData.setSlug(gameDTO.getSlug());
        gameData.setName(gameDTO.getName());
        gameData.setReleased(gameDTO.getReleased());
        gameData.setTba(gameDTO.isTba());
        gameData.setBackgroundImage(gameDTO.getBackgroundImage());
        gameData.setPlaytime(gameDTO.getPlaytime());
        gameData.setSaturatedColor(gameDTO.getSaturatedColor());
        gameData.setDominantColor(gameDTO.getDominantColor());

        // Convert platforms, genres, tags, and screenshots to JSON or delimited strings
        gameData.setPlatforms(convertListToString(gameDTO.getPlatforms()));
        gameData.setGenres(convertListToString(gameDTO.getGenres()));
        gameData.setTags(convertListToString(gameDTO.getTags()));
        gameData.setScreenshots(convertListToString(gameDTO.getScreenshots()));

        gameData.setClip(gameDTO.getClip() != null ? gameDTO.getClip().toString() : null);
        gameData.setLikes(gameDTO.getLikes());
        gameData.setRating(gameDTO.getRating());
        return gameData;
    }

    private <T> String convertListToString(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        try {
            // Using ObjectMapper to convert to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting list to string", e);
        }
    }

    public static GameDTO convertToGameDTO(GameData gameData) {
        // Convert the platforms, genres, tags, and screenshots to their respective DTOs
        List<PlatformInfoDTO> platformInfoDTOList = convertPlatforms(gameData.getPlatforms());
        List<GenreDTO> genreDTOList = convertGenres(gameData.getGenres());
        List<TagDTO> tagDTOList = convertTags(gameData.getTags());
        List<Screenshot> screenshotList = convertScreenshots(gameData.getScreenshots());

        return GameDTO.builder()
                .id(gameData.getId())
                .slug(gameData.getSlug())
                .name(gameData.getName())
                .released(gameData.getReleased())
                .tba(gameData.isTba())
                .backgroundImage(gameData.getBackgroundImage())
                .playtime(gameData.getPlaytime())
                .saturatedColor(gameData.getSaturatedColor())
                .dominantColor(gameData.getDominantColor())
                .platforms(platformInfoDTOList)
                .genres(genreDTOList)
                .clip(gameData.getClip()) // assuming 'clip' can be directly mapped
                .tags(tagDTOList)
                .screenshots(screenshotList)
                .likes(gameData.getLikes())
                .rating(gameData.getRating())
                .build();
    }

    private static List<PlatformInfoDTO> convertPlatforms(String platforms) {
        return convertJsonToList(platforms, PlatformInfoDTO.class);
    }

    private static List<GenreDTO> convertGenres(String genres) {
        return convertJsonToList(genres, GenreDTO.class);
    }

    private static List<TagDTO> convertTags(String tags) {
        return convertJsonToList(tags, TagDTO.class);
    }

    private static List<Screenshot> convertScreenshots(String screenshots) {
        return convertJsonToList(screenshots, Screenshot.class);
    }

    private static <T> List<T> convertJsonToList(String json, Class<T> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, new TypeReference<List<T>>(){});
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Return empty list in case of error
        }
    }
}
