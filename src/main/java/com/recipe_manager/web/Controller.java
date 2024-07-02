package com.recipe_manager.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    
    private RecipeRepo repo;

    public Controller(RecipeRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        return repo.listAllRecipes();
    }

    @GetMapping("/recipes/{id}")
    public Recipe getSpecificRecipe(@PathVariable int id) {
        return repo.listSpecificRecipe(id);
    }

    @GetMapping("/recipes/search-by-ingredient/{ingredient}")
    public List<Recipe> getRecipeByIngredient(@PathVariable String ingredient) {
        return repo.listRecipeGivenIngredient(ingredient);
    }

    @PostMapping("/recipes")
    public void createRecipe(@RequestBody Recipe recipe) {
        repo.addNewRecipe(recipe);
    }
    
}
