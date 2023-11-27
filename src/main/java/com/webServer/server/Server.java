package com.webServer.server;

import com.repositories.PostRepository;
import com.repositories.AuthorRepository;
import com.routeHandlers.Author.*;
import com.routeHandlers.Login.LoginRoute;
import com.routeHandlers.Post.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spark.*;

import static spark.Spark.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import spark.Request;
import spark.Response;
import spark.Spark;

public class Server {
    static public final String secret_key = "safasfas;kfsalhsaohsaipfjasoIfga";
    public static spark.Filter jwtFilter() {
        return (Request request, Response response) -> {
            String token = request.headers("Authorization");

            if (token == null || !token.startsWith("Bearer ")) {
                Spark.halt(401, "Unauthorized"); // No token or token format is invalid
            }

            String jwt = token.substring(7); // Remove "Bearer " prefix

            try {
                Algorithm algorithm = Algorithm.HMAC256(secret_key);
                JWT.require(algorithm).build().verify(jwt);

            } catch (Exception e) {
                Spark.halt(401, "Unauthorized"); // Token verification failed
            }
        };
    }

    public static void main(String[] args) {
        Spark.port(3000);


        SessionFactory sessionFactory = new Configuration().configure("./hibernate.cfg.xml").buildSessionFactory();

        org.hibernate.Session session = sessionFactory.openSession();

        PostRepository repository = new PostRepository(session);
        AuthorRepository authorRepository = new AuthorRepository(session);

        staticFiles.location("/public");

        before("/posts", jwtFilter());
        before("/users", jwtFilter());

        before("/posts/*", jwtFilter());
        before("/users/*", jwtFilter());
        before((request, response) -> response.type("application/json"));
        redirect.any("/legacy-posts", "/posts", Redirect.Status.MOVED_PERMANENTLY);

        get("/posts", new GetPosts(repository));

        get("/login", new LoginRoute());
        get("/posts/:id", new GetPostById(repository));
        post("/posts", new CreatePost(repository));
        delete("/posts/:id", new DeletePost(repository));
        put("/posts/:id", new UpdatePost(repository));

        get("/authors", new GetAuthors(authorRepository));
        get("/authors/:id", new GetAuthorById(authorRepository));
        get("/authors/:id/posts", new GetAuthorPosts(authorRepository));
        post("/authors", new CreateAuthor(authorRepository));
        put("/authors/:id", new UpdateAuthor(authorRepository));
        delete("/authors", new DeleteAuthor(authorRepository));
    }
}