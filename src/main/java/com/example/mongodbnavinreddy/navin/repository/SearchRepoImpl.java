package com.example.mongodbnavinreddy.navin.repository;

import com.example.mongodbnavinreddy.navin.model.Post;
import com.mongodb.client.*;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchRepoImpl implements SearchRepo{

    private final MongoClient mongoClient;

    private final MongoConverter mongoConverter;



    @Override
    public List<Post> findByText(String text) {

        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = mongoClient.getDatabase("jobPosts");
        MongoCollection<Document> collection = database.getCollection("posts");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("text",
                new Document("query", text)
                .append("path", Arrays.asList("techs", "profile", "desc")))),
                new Document("$sort",
                new Document("exp", 1L)),
                new Document("$limit", 5L)));

        result.forEach(doc -> posts.add(mongoConverter.read(Post.class, doc)));

        return posts;
    }

}
