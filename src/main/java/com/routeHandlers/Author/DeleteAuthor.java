package com.routeHandlers.Author;

import com.entities.Author;
import com.google.gson.Gson;
import com.repositories.AuthorRepository;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeleteAuthor implements Route {
    static private final Gson gson = new Gson();
    AuthorRepository repository;
    public DeleteAuthor(AuthorRepository repository) {
        this.repository = repository;
    }
    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            int postId = Integer.parseInt(request.params(":id"));
            Author author = this.repository.deleteAuthor(postId);

            if (author == null) {
                throw new Exception("Not found");
            }
            return gson.toJson(author);
        } catch (Exception err) {
            return gson.toJson(err.getMessage());
        }
    }
}
