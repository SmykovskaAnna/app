package com.example.recipes.service;

import com.example.recipes.model.Comment;
import com.example.recipes.model.Recipe;
import com.example.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepo;

    public List<Recipe> listAll(String keyword) {
        return (keyword != null) ? recipeRepo.findByTitleContainingIgnoreCase(keyword) : recipeRepo.findAll();
    }

    public void save(Recipe recipe) {
        recipeRepo.save(recipe);
    }

    public Recipe get(Long id) {
        return recipeRepo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        recipeRepo.deleteById(id);
    }

    public double getAverageRating(Recipe recipe) {
        List<Comment> comments = recipe.getComments();
        if (comments.isEmpty()) return 0;
        return comments.stream().mapToInt(Comment::getRating).average().orElse(0);
    }
}