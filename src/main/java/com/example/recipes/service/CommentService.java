package com.example.recipes.service;

import com.example.recipes.model.Comment;
import com.example.recipes.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepo;

    public void save(Comment comment) {
        commentRepo.save(comment);
    }

    public List<Comment> listByRecipeId(Long recipeId) {
        return commentRepo.findByRecipeId(recipeId);
    }
}