package com.backend.sample.demo.service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.backend.sample.demo.entity.log.LogEntity;
import com.backend.sample.demo.repository.LogRepository;
import com.backend.sample.demo.vo.log.LogResponse;
import com.backend.sample.demo.vo.log.LogResponses;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Service
public class LogService {
    private final LogRepository logRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public LogService(LogRepository logRepository, ObjectMapper objectMapper) {
        this.logRepository = logRepository;
        this.objectMapper = objectMapper;
    }

    @Cacheable(cacheNames = "allLogs", key = "'allLogsKey'", sync = true, cacheManager = "rcm")
    public LogResponses findAll() {
        log.info("==== findAll 호출 ====");

        List<LogEntity> logs = logRepository.findAll();

        try {
            List<LogResponse> convertedLogs = objectMapper.readValue(
                objectMapper.writeValueAsString(logs),
                new TypeReference<>() {}
            );

            return new LogResponses(convertedLogs);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @CacheEvict(cacheNames = "allLogs", allEntries = true, beforeInvocation = true, cacheManager = "rcm")
    public void removeAll() {
        log.info("==== removeAll 호출 ====");
    }
}
