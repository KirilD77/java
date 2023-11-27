package com.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    // Constructors, getters, and setters

    // Assuming you have a constructor with parameters
    public Author(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author() {

    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
