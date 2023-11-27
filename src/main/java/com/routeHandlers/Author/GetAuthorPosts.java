package com.routeHandlers.Author;

import com.entities.Author;
import com.entities.Post;
import com.google.gson.Gson;
import com.repositories.AuthorRepository;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class GetAuthorPosts implements Route {
    static private final Gson gson = new Gson();
    AuthorRepository repository;

    public GetAuthorPosts(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            int userId = Integer.parseInt(request.params(":id"));
            List posts = this.repository.getAuthorPosts(userId);

            return gson.toJson(posts);
        } catch (Exception err) {
            response.status(404);
            return gson.toJson(err.getMessage());
        }
    }
}