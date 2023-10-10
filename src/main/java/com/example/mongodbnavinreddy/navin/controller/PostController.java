package com.example.mongodbnavinreddy.navin.controller;

import com.example.mongodbnavinreddy.navin.model.Post;
import com.example.mongodbnavinreddy.navin.repository.PostRepo;
import com.example.mongodbnavinreddy.navin.repository.SearchRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
// its a controller
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    private final PostRepo postRepo;
    private final SearchRepo searchRepo;

    @ApiIgnore // -> canceling the custom api of swagger at localhost:8080
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    // posts/java
    @GetMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text) {
        return searchRepo.findByText(text);
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post) {
        return postRepo.save(post);
    }

}
