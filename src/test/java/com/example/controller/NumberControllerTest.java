package com.example.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;;

class NumberControllerTest {

    @InjectMocks
    private NumberController numberController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateNMaxNumber_ValidInput() throws Exception {
        String filePath = "src/test/resources/numbers_example.xlsx";
        int N = 3;

        ResponseEntity<String> response = numberController.calculateNMaxNumber(filePath, N);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testCalculateNMaxNumber_InvalidFilePath() {
        String filePath = "invalid/path/to/file.xlsx";
        int N = 3;

        ResponseEntity response = numberController.calculateNMaxNumber(filePath, N);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Failed to read the Excel file:") );
    }

    @Test
    void testCalculateNMaxNumber_InsufficientNumbers() throws Exception {
        String filePath = "src/test/resources/numbers_example.xlsx";
        int N = 555;

        ResponseEntity<String> response = numberController.calculateNMaxNumber(filePath, N);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("There are less than 555 numbers in the file."));
    }

}
