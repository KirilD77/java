package com.routeHandlers.Post;

import com.entities.Post;
import com.repositories.PostRepository;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

public class GetPostByIdTest {
  @Test
  public void testGetPostById() {
    Post post = new Post();

    post.SetId(1);
    post.SetBody("body");
    post.SetTitle("title");
    post.SetAuthorId(1);

    Session session = Mockito.mock(Session.class);
    Mockito.when(session.get(Post.class, 1)).thenReturn(post);

    PostRepository repository = new PostRepository(session);

    Assertions.assertEquals(post, repository.getPostById(1));
    Assertions.assertNull(repository.getPostById(2));
  }
}
