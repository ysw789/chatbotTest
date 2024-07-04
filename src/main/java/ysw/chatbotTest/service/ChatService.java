package ysw.chatbotTest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ysw.chatbotTest.dto.ChatGPTRequest;
import ysw.chatbotTest.dto.ChatGPTResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    public String getResponse(String prompt) {
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse response = restTemplate.postForObject(apiUrl, request, ChatGPTResponse.class);
        log.info("response = {}", response);
        return response.getChoices().get(0).getMessage().getContent();
    }
}
