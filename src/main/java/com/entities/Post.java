package com.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "body")
    private String body;

    @Column(name = "author_id")
    private Integer author_id;

    public String GetTitle() {
        return this.title;
    }
    public String GetBody() {
        return this.body;
    }
    public Integer GetAuthorId() {
        return this.author_id;
    }

    public Integer GetId() {
        return id;
    }

    public void SetTitle(String title) { this.title = title; }
    public void SetBody(String body) { this.body = body; }
    public void SetAuthorId(Integer author_id) { this.author_id = author_id; }

    public void SetId(Integer id) {
        this.id = id;
    }
}
