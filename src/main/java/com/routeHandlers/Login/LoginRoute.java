package com.routeHandlers.Login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginRoute implements Route {
    static public final String secret_key = "safasfas;kfsalhsaohsaipfjasoIfga";

        @Override
        public Object handle(Request request, Response response) throws Exception {
            // Generate JWT token
            Algorithm algorithm = Algorithm.HMAC256(secret_key);
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", "some-user-name")
                    .sign(algorithm);

            response.type("application/json");
            return "{\"token\": \"" + token + "\"}";
        }
}
