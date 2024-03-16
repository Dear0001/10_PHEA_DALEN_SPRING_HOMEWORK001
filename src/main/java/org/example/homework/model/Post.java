package org.example.homework.model;

import java.time.LocalDateTime;
import java.util.List;

public class Post {
    private static int nextId = 0;
    private int id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime creationDate;
    private List<String> tags;

    public Post(String title, String content, String author, List<String> tags) {
        this.id = nextId++;
        this.title = title;
        this.content = content;
        this.author = author;
        this.creationDate = LocalDateTime.now();
        this.tags = tags;
    }

    public int getId() {
        return id;
    }
    public static int getNextId(){
        return nextId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
