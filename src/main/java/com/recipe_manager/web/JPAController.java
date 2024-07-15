package com.recipe_manager.web;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class JPAController {
    
    private RecipeJPARepo jparepo;

    public JPAController(RecipeJPARepo jpaRepo) {
        this.jparepo = jpaRepo;
    }

    @GetMapping("/jpa/recipes")
    public List<Recipe> getAllRecipes() {
        return jparepo.findAll();
    }

    @GetMapping("/jpa/recipes/{id}")
    public EntityModel<Recipe> getSpecificRecipe(@PathVariable int id) {
        Optional<Recipe> recipe = jparepo.findById(id);
        EntityModel<Recipe> wrappedRecipe = EntityModel.of(recipe.get());

        if (recipe.isEmpty()) {
            throw new RecipeNotFoundException("id=" + id);
        }

        WebMvcLinkBuilder linkOne = linkTo(methodOn(this.getClass()).getAllRecipes());
        wrappedRecipe.add(linkOne.withRel("all-recipes"));
        WebMvcLinkBuilder linkTwo = linkTo(methodOn(this.getClass()).updateRecipeRating(id, recipe.get().getRating()));
        wrappedRecipe.add(linkTwo.withRel("update-rating"));
        
        return wrappedRecipe;
    }

    @GetMapping("/jpa/recipes/search-by-ingredient/{ingredient}")
    public List<Recipe> getRecipeByIngredient(@PathVariable String ingredient) {
        List<Recipe> recipes = jparepo.listRecipeGivenIngredient(ingredient);

        if (recipes.isEmpty()) {
            throw new RecipeNotFoundException("ingredient=" + ingredient);
        } else {
            return recipes;
        }
    }

    @PutMapping("/jpa/recipes/{id}")
    public Recipe updateRecipeRating(@PathVariable int id, @RequestParam double rating) {
        Optional<Recipe> recipe = jparepo.findById(id);
        recipe.get().setRating(rating);

        return recipe.get();
    }

    @PostMapping("/jpa/recipes")
    public ResponseEntity<Object> createRecipe(@Valid @RequestBody Recipe recipe) {
        jparepo.save(recipe);

        URI location = URI.create("/users/" + recipe.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/recipes/{id}")
    public void deleteRecipe(@PathVariable int id) {
        jparepo.deleteById(id);
    }

}
