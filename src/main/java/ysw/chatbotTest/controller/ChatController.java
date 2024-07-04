package ysw.chatbotTest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ysw.chatbotTest.ApiResponse.ApiResponse;
import ysw.chatbotTest.service.ChatService;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/chat")
    public ResponseEntity<ApiResponse<String>> chat(@RequestBody String prompt) {
        return ResponseEntity.ok(new ApiResponse<>(chatService.getResponse(prompt)));
    }

}
