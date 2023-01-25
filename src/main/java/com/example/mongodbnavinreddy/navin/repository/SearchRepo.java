package com.example.mongodbnavinreddy.navin.repository;

import com.example.mongodbnavinreddy.navin.model.Post;

import java.util.List;

public interface SearchRepo {

    List<Post> findByText(String text);
}
