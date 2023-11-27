package com.routeHandlers.Post;

import com.entities.Post;
import com.google.gson.Gson;
import com.repositories.PostRepository;
import spark.Request;
import spark.Response;
import spark.Route;

public class CreatePost implements Route {
    static private final Gson gson = new Gson();

    PostRepository repository;
    public CreatePost(PostRepository repository) {
        this.repository = repository;
    }

    public Object handle(Request req, Response res) throws Exception {
        try {
            Post createdPost = this.repository.savePost(gson.fromJson(req.body(), Post.class));
            if(createdPost == null) {
                throw new Exception("Error");
            }
            return gson.toJson(createdPost);

        } catch (Exception err) {
            return gson.toJson(err.getMessage());
        }
    }
}
