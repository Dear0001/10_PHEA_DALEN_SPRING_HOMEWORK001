package org.example.homework.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseListPost<T> {
    private final String message;
    private final List<T> payload;
    private final HttpStatus status;
    private final LocalDateTime time;

    public PostResponseListPost(String message, List<T> payload, HttpStatus status, LocalDateTime time) {
        this.message = message;
        this.payload = payload;
        this.status = status;
        this.time = time;
    }

}
