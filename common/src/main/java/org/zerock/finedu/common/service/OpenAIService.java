package org.zerock.finedu.common.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.zerock.finedu.common.config.OpenAIConfig;
import org.zerock.finedu.common.dto.request.OpenAIRequest;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAIService {
    private final OpenAIConfig openAIConfig;
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${openai.model}")
    private String model;

    public OpenAIService(OpenAIConfig openAIConfig) {
        this.openAIConfig = openAIConfig;
    }

    private HttpHeaders createHeaders() {
        return openAIConfig.createHttpHeaders();
    }

    public String getResponseFromOpenAI(OpenAIRequest openAIRequest) {
        HttpHeaders headers = createHeaders();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model);

        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", openAIRequest.getPrompt());
        requestBody.put("messages", new Object[] { userMessage });

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = openAIConfig.restTemplate()
                .exchange(OPENAI_API_URL, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                JsonNode root = objectMapper.readTree(response.getBody());
                JsonNode choices = root.path("choices").get(0);
                JsonNode message = choices.path("message");
                return message.path("content").asText();
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse response from OpenAI", e);
            }
        } else {
            throw new RuntimeException("Failed to fetch response from OpenAI: " + response.getStatusCode());
        }
    }
}
