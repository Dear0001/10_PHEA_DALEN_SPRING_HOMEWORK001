package org.example.homework.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.List;

public class ResponseUtils {

    public static ResponseEntity<?> createListResponse(String message, List<?> payload, HttpStatus status) {
        return ResponseEntity.status(status).body(new PostResponsePost<>(message, payload, status, LocalDateTime.now()));
    }
}
