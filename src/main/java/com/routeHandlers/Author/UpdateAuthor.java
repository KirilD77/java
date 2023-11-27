package com.routeHandlers.Author;

import com.entities.Author;
import com.google.gson.Gson;
import com.repositories.AuthorRepository;
import spark.Request;
import spark.Response;
import spark.Route;

public class UpdateAuthor implements Route {
    static private final Gson gson = new Gson();
    AuthorRepository repository;
    public UpdateAuthor(AuthorRepository repository) {
        this.repository = repository;
    }
    @Override
    public Object handle(Request req, Response res) throws Exception {
        try {
            int userId = Integer.parseInt(req.params(":id"));
            Author payload = gson.fromJson(req.body(), Author.class);
            Author author = this.repository.updateAuthor(userId, payload);

            if (author == null) {
                throw new Exception("Not found");
            }

            return gson.toJson(author);
        } catch (Exception err) {
            return gson.toJson(err.getMessage());
        }
    }

}
