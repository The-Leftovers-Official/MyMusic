package com.ciandt.summit.bootcamp2022.adapter.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(CacheController.class)
class CacheControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String urlTemplate = "/api/cache/aW52YWxpZGEgcmVsYXTDs3JpbyBkZSB2ZW5kYXM";

    @Test
    public void shouldInvalidateCache() throws Exception {
        mockMvc
                .perform(get(urlTemplate))
                .andExpect(status().isOk());
    }
}