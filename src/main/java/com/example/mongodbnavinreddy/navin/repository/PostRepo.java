package com.example.mongodbnavinreddy.navin.repository;

import com.example.mongodbnavinreddy.navin.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepo extends MongoRepository<Post, String> { }
