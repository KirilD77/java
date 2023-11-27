package com.routeHandlers.Post;

import com.entities.Post;
import com.google.gson.Gson;
import com.repositories.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spark.Request;
import spark.Response;

public class CreatePostTest {

  @Test
  public void test() {
    Gson gson = new Gson();
    PostRepository repository = Mockito.mock(PostRepository.class);
    Request request = Mockito.mock(Request.class);
    Response response = Mockito.mock(Response.class);

    Post post = new Post();
    post.SetBody("Body");
    post.SetTitle("Title");
    post.SetAuthorId(1);



    CreatePost createPostHandler = new CreatePost(repository);
    Mockito.when(repository.savePost(post)).thenReturn(null);
    Mockito.when(request.body()).thenReturn(gson.toJson(post));


    try {
      Post savedPost = gson.fromJson(createPostHandler.handle(request, response).toString(), Post.class);
      Assertions.assertEquals(post, savedPost);
    } catch(Exception err) {
      System.out.println(err.getMessage());
    }
  }
}
