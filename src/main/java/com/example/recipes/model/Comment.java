package com.example.recipes.model;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String content;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {

        this.rating = rating;
    }

    public Recipe getRecipe() {
        return recipe;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}