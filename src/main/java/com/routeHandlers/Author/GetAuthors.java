package com.routeHandlers.Author;

import com.google.gson.Gson;
import com.repositories.AuthorRepository;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetAuthors implements Route {
    static private final Gson gson = new Gson();

    AuthorRepository repository;

    public GetAuthors(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            return gson.toJson(repository.getAuthors());
        } catch (Exception err) {
            response.status(404);
            return gson.toJson(err.getMessage());
        }
    }
}