package com.routeHandlers.Author;

import com.entities.Author;
import com.google.gson.Gson;
import com.repositories.AuthorRepository;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetAuthorById implements Route {
    static private final Gson gson = new Gson();
    AuthorRepository repository;

    public GetAuthorById(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            int userId = Integer.parseInt(request.params(":id"));
            Author author = this.repository.getAuthorById(userId);

            if (author == null) {
                throw new Exception("Not found");
            }

            return gson.toJson(author);
        } catch (Exception err) {
            response.status(404);
            return gson.toJson(err.getMessage());
        }
    }
}