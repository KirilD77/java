package com.repositories;

import com.entities.Author;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AuthorRepository {
    private final Session session;

    public AuthorRepository(Session session) {
        this.session = session;
    }

    public List<Author> getAuthors(){
        try {
            return this.session.createQuery("FROM Author", Author.class).getResultList();
        } catch (Exception err) {
            return null;
        }
    }
    public Author getAuthorById(int userId) {
            try {
                return this.session.get(Author.class, userId);
            } catch (Exception err) {
                return null;
            }
    }

    public Author updateAuthor(int userId, Author payload) {
        try {

            Transaction transaction = session.beginTransaction();

            Author author = session.get(Author.class, userId);

            if (payload.getName() != null) {
                author.setName(payload.getName());
            }

            session.update(author);
            transaction.commit();
            return author;
        } catch (Exception err) {
            return null;
        }

    }

    public Author deleteAuthor(int userId) {
        try {
            Transaction transaction = session.beginTransaction();

            Author authorToDelete = session.get(Author.class, userId);

            if (authorToDelete != null) {
                session.delete(authorToDelete);
            }

            transaction.commit();
            return authorToDelete;
        } catch (Exception err) {
            return null;
        }
    }

    public Author saveAuthor(Author author) {
        try {
            Transaction transaction = session.beginTransaction();

            session.persist(author);
            transaction.commit();

            return author;
        } catch (Exception err) {
            return null;
        }
    }

    public List getAuthorPosts(Integer authorId) {
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Post WHERE author_id = :authorId");
            query.setParameter("authorId", authorId);

            List posts = query.getResultList();
            System.out.println(posts.size());
            transaction.commit();

            return posts;

        } catch (Exception err) {
            System.out.println(err.getMessage());
            return null;
        }
    }
}
