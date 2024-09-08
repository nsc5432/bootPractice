package com.backend.sample.demo.redis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class LogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/log/removeAll"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/log/findAll"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.logs").isArray());

        Cache cache = cacheManager.getCache("allLogs");
        assertThat(cache).isNotNull();

        Cache.ValueWrapper cachedValue = cache.get("allLogsKey");
        assertThat(cachedValue).isNotNull();

        mockMvc.perform(get("/log/findAll"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.logs").isArray());
    }
}