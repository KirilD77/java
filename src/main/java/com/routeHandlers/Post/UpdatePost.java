package com.routeHandlers.Post;

import com.entities.Post;
import com.google.gson.Gson;
import com.repositories.PostRepository;
import spark.Request;
import spark.Response;
import spark.Route;

public class UpdatePost implements Route {
    static private final Gson gson = new Gson();
    PostRepository repository;
    public UpdatePost(PostRepository repository) {
        this.repository = repository;
    }
    @Override
    public Object handle(Request req, Response res) throws Exception {
        try {
            int postId = Integer.parseInt(req.params(":id"));
            Post payload = gson.fromJson(req.body(), Post.class);
            Post post = this.repository.updatePost(postId, payload);

            if (post == null) {
                throw new Exception("Not found");
            }

            return gson.toJson(post);
        } catch (Exception err) {
            return gson.toJson(err.getMessage());
        }
    }

}
