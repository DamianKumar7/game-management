package com.game.social.discovery.game_management.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.social.discovery.game_management.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.game.social.discovery.game_management.Constants.GameServiceConstants.RAWG_BASE_URI;

@Service
public class GameService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${rawg.api.key}")
    String RAWG_API_KEY;

    public GameResponseDTO getAllGames(String pageId){
        String url = String.format("%s/games?key=%s&page=%s",RAWG_BASE_URI,RAWG_API_KEY,pageId);
        ResponseEntity<String> rawgApiResponse = restTemplate.getForEntity(url,String.class);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            GameResponseDTO gameResponseDTO  = new GameResponseDTO();
            JsonNode rootNode = objectMapper.readTree(rawgApiResponse.getBody());
            JsonNode resultsNode = rootNode.path("results");
            List<GameDTO> gameDTOList = new ArrayList<>();
            for(JsonNode result: resultsNode){
                GameDTO gameDTO = new GameDTO().builder()
                        .id(result.path("id").asText())
                        .name(result.path("name").asText())
                        .released(result.path("released").asText())
                        .backgroundImage(result.path("background_image").asText())
                        .build();
                List<TagDTO> tags = new ArrayList<>();
                JsonNode tagsNode = result.path("tags");
                for (JsonNode tagNode : tagsNode) {
                    String tagName = tagNode.path("name").asText();
                    Long tagId = tagNode.path("id").asLong();


                    TagDTO tagDTO = new TagDTO();
                    tagDTO.setName(tagName);
                    tagDTO.setId(tagId);
                    tags.add(tagDTO);
                }
                gameDTO.setTags(tags);
                List<GenreDTO> genres = new ArrayList<>();
                JsonNode genreNode =  result.path("genres");
                for(JsonNode genre: genreNode){
                    String genreName = genre.path("name").asText();
                    GenreDTO genreDTO = new GenreDTO();
                    genreDTO.setName(genreName);
                    genres.add(genreDTO);
                }
                gameDTO.setGenres(genres);
                List<Screenshot> screenshots = new ArrayList<>();
                JsonNode screenshotNode = result.path("short_screenshots");
                for(JsonNode screenshot: screenshotNode){
                    String id = screenshotNode.path("id").asText();
                    String uri = screenshotNode.path("image").asText();
                    Screenshot screenshotDTO = new Screenshot();
                    screenshotDTO.setId(id);
                    screenshotDTO.setImageURI(uri);
                    screenshots.add(screenshotDTO);
                }
                gameDTO.setScreenshots(screenshots);
                gameDTOList.add(gameDTO);
            }
            gameResponseDTO.setResults(gameDTOList);
            gameResponseDTO.setSuccess(true);
            return gameResponseDTO;
        } catch (Exception e) {
            GameResponseDTO gameResponseDTO = new GameResponseDTO();
            gameResponseDTO.setSuccess(false);
            gameResponseDTO.setErrorMessage(e.getMessage());
            return gameResponseDTO;
        }
    }

    public GameDTO getGameDataById(String gameId) {
        String url = String.format("%s/games/%s?key=%s",RAWG_BASE_URI,gameId,RAWG_API_KEY);
        ResponseEntity<String> rawgApiResponse = restTemplate.getForEntity(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        GameDTO gameDTO;
        try {
            JsonNode result = objectMapper.readTree(rawgApiResponse.getBody());
            gameDTO = new GameDTO().builder()
                    .id(result.path("id").asText())
                    .name(result.path("name").asText())
                    .released(result.path("released").asText())
                    .backgroundImage(result.path("background_image").asText())
                    .build();
            List<TagDTO> tags = new ArrayList<>();
            JsonNode tagsNode = result.path("tags");
            for (JsonNode tagNode : tagsNode) {
                String tagName = tagNode.path("name").asText();
                Long tagId = tagNode.path("id").asLong();


                TagDTO tagDTO = new TagDTO();
                tagDTO.setName(tagName);
                tagDTO.setId(tagId);
                tags.add(tagDTO);
            }
            gameDTO.setTags(tags);
            List<GenreDTO> genres = new ArrayList<>();
            JsonNode genreNode =  result.path("genres");
            for(JsonNode genre: genreNode){
                String genreName = genre.path("name").asText();
                GenreDTO genreDTO = new GenreDTO();
                genreDTO.setName(genreName);
                genres.add(genreDTO);
            }
            gameDTO.setGenres(genres);
            List<Screenshot> screenshots = new ArrayList<>();
            JsonNode screenshotNode = result.path("short_screenshots");
            for(JsonNode screenshot: screenshotNode){
                String id = screenshotNode.path("id").asText();
                String uri = screenshotNode.path("image").asText();
                Screenshot screenshotDTO = new Screenshot();
                screenshotDTO.setId(id);
                screenshotDTO.setImageURI(uri);
                screenshots.add(screenshotDTO);
            }
            gameDTO.setScreenshots(screenshots);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return gameDTO;
    }
}
