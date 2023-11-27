package com.routeHandlers.Author;

import com.entities.Author;
import com.google.gson.Gson;
import com.repositories.AuthorRepository;
import spark.Request;
import spark.Response;
import spark.Route;

public class CreateAuthor implements Route {
    static private final Gson gson = new Gson();

    AuthorRepository repository;
    public CreateAuthor(AuthorRepository repository) {
        this.repository = repository;
    }

    public Object handle(Request req, Response res) throws Exception {
        try {
            Author createduser = this.repository.saveAuthor(gson.fromJson(req.body(), Author.class));
            if(createduser == null) {
                throw new Exception("Error");
            }
            return gson.toJson(createduser);

        } catch (Exception err) {
            return gson.toJson(err.getMessage());
        }
    }
}
