package com.backend.sample.demo.controller;

import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.sample.demo.service.LogService;
import com.backend.sample.demo.vo.log.LogResponses;

@RequestMapping("/log")
@RestController
@RequiredArgsConstructor
public class LogController {
    private final LogService logService;

    @GetMapping("findAll")
    public ResponseEntity<LogResponses> findAll() {
        LogResponses logs = logService.findAll();
        return ResponseEntity.ok(logs);
    }

    @GetMapping("removeAll")
    public void removeAll() {
        logService.removeAll();
    }
}