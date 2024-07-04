package ysw.chatbotTest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ysw.chatbotTest.dto.ChatGPTRequest;
import ysw.chatbotTest.dto.ChatGPTResponse;
import ysw.chatbotTest.dto.Message;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final List<Message> conversationStore = new ArrayList<>();

    public String getResponse(String prompt) {
        if (conversationStore.size() > 10)
            summarizeConversation();

        conversationStore.add(new Message("user", prompt));
        ChatGPTRequest request = new ChatGPTRequest(model, conversationStore);
        ChatGPTResponse response = restTemplate.postForObject(apiUrl, request, ChatGPTResponse.class);
        conversationStore.add(response.getChoices().get(0).getMessage());
        return response.getChoices().get(0).getMessage().getContent();
    }

    public void summarizeConversation() {
        conversationStore.add(new Message("user",
                "Summarize the main conversations in Korean, in order and including everything, in 200 characters or less."));
        ChatGPTRequest request = new ChatGPTRequest(model, conversationStore);
        ChatGPTResponse response = restTemplate.postForObject(apiUrl, request, ChatGPTResponse.class);
        // log.info("Summary: {}", response.getChoices().get(0).getMessage().getContent());
        conversationStore.clear();
        conversationStore.add(new Message("system", response.getChoices().get(0).getMessage().getContent()));
    }
}
