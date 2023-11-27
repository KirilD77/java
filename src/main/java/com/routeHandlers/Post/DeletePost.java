package com.routeHandlers.Post;

import com.entities.Post;
import com.google.gson.Gson;
import com.repositories.PostRepository;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeletePost implements Route {
    static private final Gson gson = new Gson();
    PostRepository repository;
    public DeletePost(PostRepository repository) {
        this.repository = repository;
    }
    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            int postId = Integer.parseInt(request.params(":id"));
            Post post = this.repository.deletePost(postId);

            if (post == null) {
                throw new Exception("Not found");
            }
            return gson.toJson(post);
        } catch (Exception err) {
            return gson.toJson(err.getMessage());
        }
    }
}
