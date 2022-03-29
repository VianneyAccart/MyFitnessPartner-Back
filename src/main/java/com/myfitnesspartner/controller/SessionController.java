package com.myfitnesspartner.controller;

import com.myfitnesspartner.dto.CreateSessionDto;
import com.myfitnesspartner.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class SessionController {

    @Autowired
    SessionService sessionService;

    @PostMapping("/session/create")
    public void createSession(CreateSessionDto createSessionDto) {
        sessionService.createSession(createSessionDto);
    }
}
