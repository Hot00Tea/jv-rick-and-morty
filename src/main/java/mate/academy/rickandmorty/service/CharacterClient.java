package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import mate.academy.rickandmorty.dto.external.CharacterInputDto;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class CharacterClient {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CharacterInputDto getCharacter (String url) {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();

            if (statusCode < 200 || statusCode >= 300) {
                System.err.println("Invalid response from API: " + response.body());
                return null;
            }

            return objectMapper.readValue(response.body(), CharacterInputDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
