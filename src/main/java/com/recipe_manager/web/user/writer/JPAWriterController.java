package com.recipe_manager.web.user.writer;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recipe_manager.web.recipe.Recipe;
import com.recipe_manager.web.recipe.RecipeJPARepo;
import com.recipe_manager.web.user.reader.JPAReaderController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/writer")
public class JPAWriterController extends JPAReaderController {
    
    private RecipeJPARepo repo;

    public JPAWriterController(RecipeJPARepo repo) {
        super(repo);
    }

    @PostMapping("/recipes")
    public ResponseEntity<Object> createRecipe(@Valid @RequestBody Recipe recipe) {
        repo.save(recipe);

        URI location = URI.create("/users/" + recipe.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/recipes/{id}")
    public void deleteRecipe(@PathVariable int id) {
        repo.deleteById(id);
    }

    @PutMapping("/recipes/{id}/update-ingredients")
    public Recipe updateIngredients(@PathVariable int id, @RequestParam String newIngredients) {
        Optional<Recipe> recipe = repo.findById(id);
        recipe.get().setIngredients(newIngredients);

        return recipe.get();
    }
    
    // update methods only taken in via params currently

    @PutMapping("/recipes/{id}/update-steps")
    public Recipe updateSteps(@PathVariable int id, @RequestParam String newSteps) {
        Optional<Recipe> recipe = repo.findById(id);
        recipe.get().setSteps(newSteps);

        return recipe.get();
    }

    @PutMapping("/recipes/{id}/update-title")
    public Recipe updateTitle(@PathVariable int id, @RequestParam String newTitle) {
        Optional<Recipe> recipe = repo.findById(id);
        recipe.get().setTitle(newTitle);

        return recipe.get();
    }

    @PutMapping("/recipes/{id}/update-category")
    public Recipe updateCategory(@PathVariable int id, @RequestParam String newCategory) {
        Optional<Recipe> recipe = repo.findById(id);
        recipe.get().setCategory(newCategory);

        return recipe.get();
    }
}
