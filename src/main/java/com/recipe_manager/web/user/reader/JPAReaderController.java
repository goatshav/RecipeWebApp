package com.recipe_manager.web.user.reader;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recipe_manager.web.exceptions.RecipeNotFoundException;
import com.recipe_manager.web.recipe.Recipe;
import com.recipe_manager.web.recipe.RecipeJPARepo;


@RestController
public class JPAReaderController {
    
    @Autowired
    private RecipeJPARepo repo;

    public JPAReaderController(RecipeJPARepo repo) {
        this.repo = repo;
    }

    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        return repo.findAll();
    }

    @GetMapping("/recipes/{id}")
    public EntityModel<Recipe> getSpecificRecipe(@PathVariable int id) {
        Optional<Recipe> recipe = repo.findById(id);
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
        Optional<Recipe> recipe = repo.findById(id);
        recipe.get().setRating(rating);

        return recipe.get();
    }    

}
