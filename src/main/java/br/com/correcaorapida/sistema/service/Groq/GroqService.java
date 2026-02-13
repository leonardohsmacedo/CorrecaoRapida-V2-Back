package br.com.correcaorapida.sistema.service.Groq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class GroqService {

    private final String API_URL = "";
    private final String API_KEY = "";
    private final String modelo = "llama-3.3-70b-versatile";

    public JsonNode criarQuestao(String enunciado) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);

        // O corpo da requisição PRECISA seguir este formato de mensagens
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", enunciado);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", modelo);
        requestBody.put("messages", Collections.singletonList(message));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);
            return conteudoDaIAemJson(response);
        } catch (Exception e) {
            return null;
        }
    }

    private JsonNode conteudoDaIAemJson(ResponseEntity<String> JsonCompleto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(JsonCompleto.getBody());
        String questaoemJson = root.path("choices").get(0).path("message").path("content").asText();
        return mapper.readTree(questaoemJson);
    }
}
