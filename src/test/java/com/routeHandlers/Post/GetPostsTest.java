package com.routeHandlers.Post;

import org.hibernate.Session;
import com.entities.Post;
import com.repositories.PostRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class GetPostsTest {
  @Test
  public void testGetPosts() {
    Post post = new Post();

    post.SetAuthorId(1);
    post.SetBody("Some body");
    post.SetTitle("Some title");

    List<Post> postsList = new ArrayList<>();
    postsList.add(post);

    Session session = Mockito.mock(Session.class);
    Query query = Mockito.mock(Query.class);

    Mockito.when(session.createQuery("FROM Post", Post.class)).thenReturn(query);
    Mockito.when(query.getResultList()).thenReturn(postsList);

    PostRepository repository = new PostRepository(session);
    Assertions.assertEquals(repository.getPosts(), postsList);
  }
}