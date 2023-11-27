package com.repositories;

import com.entities.Post;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PostRepository {
  private final Session session;

  public PostRepository(Session session) {
    this.session = session;
  }

  public List<Post> getPosts() {
    try {
      return this.session.createQuery("FROM Post", Post.class).getResultList();
    } catch (Exception err) {
      return null;
    }
  }

  public Post savePost(Post post) {
    try {
      Transaction transaction = session.beginTransaction();
      if (post.GetAuthorId() == null) {
        throw new Exception();
      }
      session.persist(post);
      transaction.commit();
      return post;
    } catch (Exception err) {
      return null;
    }
  }

  public Post getPostById(int postId) {
    try {
      return session.get(Post.class, postId);
    } catch (Exception err) {
      return null;
    }
  }

  public Post updatePost(int postId, Post payload) {
    try {
      Transaction transaction = session.beginTransaction();
      Post post = session.get(Post.class, postId);
      if (payload.GetTitle() != null) {
        post.SetTitle(payload.GetTitle());
      }
      if (payload.GetBody() != null) {
        post.SetBody(payload.GetBody());
      }
      if (payload.GetAuthorId() != null) {
        post.SetAuthorId(payload.GetAuthorId());
      } else {
        throw new Exception();
      }
      session.update(post);
      transaction.commit();
      return post;
    } catch (Exception err) {
      return null;
    }
  }

  public Post deletePost(long postId) {
    try {
      Transaction transaction = session.beginTransaction();

      Post postToDelete = session.get(Post.class, postId);
      if (postToDelete != null) {
        session.delete(postToDelete);
      }
      transaction.commit();
      return postToDelete;
    } catch (Exception err) {
      return null;
    }
  }
}
