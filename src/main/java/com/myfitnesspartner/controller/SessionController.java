package com.myfitnesspartner.controller;

import com.myfitnesspartner.dto.CreateSessionDto;
import com.myfitnesspartner.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class SessionController {

    @Autowired
    SessionService sessionService;

    @PostMapping("/session/create")
    public ResponseEntity<String> createSession(@Valid @RequestBody CreateSessionDto createSessionDto) {
        return sessionService.createSession(createSessionDto);
    }
}
