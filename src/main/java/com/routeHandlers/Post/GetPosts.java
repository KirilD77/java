package com.routeHandlers.Post;

import com.google.gson.Gson;
import com.repositories.PostRepository;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetPosts implements Route {
    static private final Gson gson = new Gson();

    PostRepository repository;

    public GetPosts(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            return gson.toJson(repository.getPosts());
        } catch (Exception err) {
            response.status(404);
            return gson.toJson(err.getMessage());
        }
    }
}