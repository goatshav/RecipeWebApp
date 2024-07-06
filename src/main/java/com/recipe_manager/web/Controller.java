package com.recipe_manager.web;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recipe_manager.web.exceptions.RecipeNotFoundException;

import jakarta.validation.Valid;

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
    public EntityModel<Recipe> getSpecificRecipe(@PathVariable int id) {
        Recipe recipe = repo.listSpecificRecipe(id);
        EntityModel<Recipe> wrappedRecipe = EntityModel.of(recipe);

        if (recipe == null) {
            throw new RecipeNotFoundException("id=" + id);
        }

        WebMvcLinkBuilder linkOne = linkTo(methodOn(this.getClass()).getAllRecipes());
        wrappedRecipe.add(linkOne.withRel("all-recipes"));
        WebMvcLinkBuilder linkTwo = linkTo(methodOn(this.getClass()).updateRecipeRating(id, recipe.getRating()));
        wrappedRecipe.add(linkTwo.withRel("update-rating"));
        
        return wrappedRecipe;
    }

    @GetMapping("/recipes/search-by-ingredient/{ingredient}")
    public List<Recipe> getRecipeByIngredient(@PathVariable String ingredient) {
        List<Recipe> recipes = repo.listRecipeGivenIngredient(ingredient);

        if (recipes.isEmpty()) {
            throw new RecipeNotFoundException("ingredient=" + ingredient);
        } else {
            return recipes;
        }
    }

    @PutMapping("/recipes/{id}")
    public Recipe updateRecipeRating(@PathVariable int id, @RequestParam double rating) {
        Recipe recipe = repo.listSpecificRecipe(id);
        recipe.setRating(rating);

        return recipe;
    }

    @PostMapping("/recipes")
    public ResponseEntity<Object> createRecipe(@Valid @RequestBody Recipe recipe) {
        repo.addNewRecipe(recipe);

        URI location = URI.create("/users/" + recipe.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/recipes/{id}")
    public void deleteRecipe(@PathVariable int id) {
        repo.deleteById(id);
    }
    
}
