package com.example.controller;

import org.apache.poi.ss.usermodel.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.PriorityQueue;

@RestController
@RequestMapping("/api")
public class NumberController {

    @PostMapping("/max-number")
    public ResponseEntity calculateNMaxNumber(@RequestParam("filePath") String filePath, @RequestParam("N") int N) {
        try {
            return retrieveNMaxNumber(filePath, N);
        } catch (IOException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    private ResponseEntity retrieveNMaxNumber(String filePath, int N) throws IOException {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(N);
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell.getCellType() == CellType.NUMERIC) {
                    int number = (int) cell.getNumericCellValue();
                    if (minHeap.size() < N) {
                        minHeap.add(number);
                    } else if (number > minHeap.peek()) {
                        minHeap.poll();
                        minHeap.add(number);
                    }
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to read the Excel file: " + e.getMessage());
        } finally {
            if (fis != null) {
                fis.close();
            }
        }

        if (minHeap.size() < N) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There are less than " + N + " numbers in the file.");
        }
        return ResponseEntity.ok(minHeap.peek());
    }
}
