package com.example.recipes.controller;

import com.example.recipes.model.Comment;
import com.example.recipes.model.Recipe;
import com.example.recipes.service.CommentService;
import com.example.recipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String viewHome(Model model, @RequestParam(required = false) String keyword) {
        List<Recipe> recipes = recipeService.listAll(keyword);
        Map<Long, Double> avgRatings = new HashMap<>();
        for (Recipe recipe : recipes) {
            avgRatings.put(recipe.getId(), recipeService.getAverageRating(recipe));
        }
        model.addAttribute("listRecipes", recipes);
        model.addAttribute("avgRatings", avgRatings);
        return "index";
    }

    @GetMapping("/new")
    public String newRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipe_form";
    }

    @PostMapping("/save")
    public String saveRecipe(@ModelAttribute Recipe recipe) {
        recipeService.save(recipe);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.get(id));
        return "recipe_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/view/{id}")
    public String viewRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.get(id));
        model.addAttribute("comments", commentService.listByRecipeId(id));
        model.addAttribute("comment", new Comment());
        return "recipe_view";
    }

    @PostMapping("/comment/save")
    public String saveComment(@ModelAttribute Comment comment, @RequestParam Long recipeId) {
        comment.setRecipe(recipeService.get(recipeId));
        commentService.save(comment);
        return "redirect:/view/" + recipeId;
    }
}