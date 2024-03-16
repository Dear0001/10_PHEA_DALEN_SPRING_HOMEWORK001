package org.example.homework.controller;

import org.example.homework.model.Post;
import org.example.homework.model.PostResponsePost;
import org.example.homework.model.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final List<Post> posts = new ArrayList<>();

    @PostMapping("/post")
    public ResponseEntity<?> addPost(@RequestBody Post post) {
        post.setId(Post.getNextId());
        post.setCreationDate(LocalDateTime.now());
        posts.add(post);
        return createResponse("This post was successfully created", post, HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "2") int pageSize) {
        List<Post> paginatedPosts = posts.stream()
                .skip((pageNo - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        return ResponseUtils.createListResponse("Get all posts successfully", paginatedPosts, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable int id) {
        Optional<Post> optionalPost = posts.stream().filter(p -> p.getId() == id).findFirst();
        if (optionalPost.isPresent()) {
            return createResponse("This post has found successfully", optionalPost.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }
    }


    @GetMapping("/search")
    public ResponseEntity<?> getPostsByTitle(@RequestParam String title) {
        List<Post> result = posts.stream().filter(p -> p.getTitle().equals(title)).collect(Collectors.toList());
        if (!result.isEmpty()) {
            return ResponseEntity.ok(createResponse("Posts with title '" + title + "' retrieved successfully", (Post) result, HttpStatus.CREATED));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Posts with title '" + title + "' not found");
        }
    }

    @GetMapping("/author")
    public ResponseEntity<?> getPostsByAuthor(@RequestParam String author) {
        List<Post> result = posts.stream().filter(p -> p.getAuthor().equals(author)).collect(Collectors.toList());
        if (!result.isEmpty()) {
            return ResponseEntity.ok(createResponse("Posts by author '" + author + "' retrieved successfully", (Post) result, HttpStatus.CREATED));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Posts by author '" + author + "' not found");
        }
    }

    @GetMapping("/tags")
    public ResponseEntity<?> getPostsByTags(@RequestParam List<String> tags) {
        List<Post> result = new ArrayList<>();
        for (String tag : tags) {
            result.addAll(posts.stream().filter(p -> p.getTags().contains(tag)).collect(Collectors.toList()));
        }
        if (!result.isEmpty()) {
            return ResponseEntity.ok(createResponse("Posts with tags '" + tags + "' retrieved successfully", (Post) result, HttpStatus.CREATED));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Posts with tags '" + tags + "' not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable int id, @RequestBody Post updatedPost) {
        Optional<Post> optionalPost = posts.stream().filter(p -> p.getId() == id).findFirst();
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setContent(updatedPost.getContent());
            existingPost.setAuthor(updatedPost.getAuthor());
            existingPost.setTags(updatedPost.getTags());

            PostResponsePost<Post> response = new PostResponsePost<>("This post was successfully updated", existingPost, HttpStatus.OK, LocalDateTime.now());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post with ID " + id + " not found");
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable int id) {
        boolean removed = posts.removeIf(p -> p.getId() == id);
        if (removed) {
            return createResponse("Congratulation your delete is successfully", null, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post with ID " + id + " not found");
        }
    }

    private <T> ResponseEntity<?> createResponse(String message, T payload, HttpStatus status) {
        PostResponsePost<T> response = new PostResponsePost<>(message, payload, status, LocalDateTime.now());
        return ResponseEntity.status(status).body(response);
    }

}
