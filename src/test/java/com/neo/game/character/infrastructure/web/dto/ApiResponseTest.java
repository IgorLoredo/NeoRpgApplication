package com.neo.game.character.infrastructure.web.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

    @Test
    void successShouldWrapData() {
        ApiResponse<String> response = ApiResponse.success("ok");
        assertTrue(response.isSuccess());
        assertEquals("ok", response.getData());
        assertNull(response.getMessage());
    }

    @Test
    void errorShouldWrapMessage() {
        ApiResponse<String> response = ApiResponse.error("fail");
        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertEquals("fail", response.getMessage());
    }

    @Test
    void errorShouldAllowData() {
        ApiResponse<String> response = ApiResponse.error("details", "fail");
        assertFalse(response.isSuccess());
        assertEquals("details", response.getData());
        assertEquals("fail", response.getMessage());
    }
}
