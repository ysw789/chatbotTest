package ysw.chatbotTest.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTRequest {
    private String model;
    private List<Message> messages;
    private double temperature;
    private int max_tokens;

    public ChatGPTRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
        this.temperature = 0.5;
        this.max_tokens = 256;
    }

    public ChatGPTRequest(String model, String prompt, double temperature, int max_tokens) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
        this.temperature = temperature;
        this.max_tokens = max_tokens;
    }
}
