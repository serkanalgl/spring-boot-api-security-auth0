package com.github.serkanalgl.api.security.auth0.controller;

import lombok.extern.slf4j.Slf4j;
import com.github.serkanalgl.api.security.auth0.dto.MessageDTO;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class MessageController {

    @GetMapping(value = "/public")
    public MessageDTO publicEndpoint() {
        return new MessageDTO("All good. You DO NOT need to be authenticated to call /messages/public");
    }

    @GetMapping(value = "/private")
    public MessageDTO privateEndpoint() {
        return new MessageDTO("All good. You can see this because you are Authenticated.");
    }

    @GetMapping(value = "/private-scoped")
    @PreAuthorize("hasAuthority('SCOPE_read:messages')")
    public MessageDTO privateScopedEndpoint() {
        return new MessageDTO("All good. You can see this because you are Authenticated with a Token granted the 'read:messages' scope");
    }

}
