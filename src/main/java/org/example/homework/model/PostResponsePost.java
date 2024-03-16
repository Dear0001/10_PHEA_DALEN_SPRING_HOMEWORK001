package org.example.homework.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class PostResponsePost<T> {
    private final String message;
    private final T payload;
    private final HttpStatus status;
    private final LocalDateTime time;

    public PostResponsePost(String message, T payload, HttpStatus status, LocalDateTime time) {
        this.message = message;
        this.payload = payload;
        this.status = status;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public T getPayload() {
        return payload;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
