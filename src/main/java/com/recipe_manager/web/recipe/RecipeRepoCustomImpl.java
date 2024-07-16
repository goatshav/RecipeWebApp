package com.recipe_manager.web.recipe;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
public class RecipeRepoCustomImpl implements RecipeRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;
    
    // helper method
    private boolean isIngredientPresent(String ingredient, String ingredientList) {
        String[] ingredients = ingredientList.split(",");
        for (String ing : ingredients) {
            if (ing.equals(ingredient)) {
                return true;
            }
        }
        return false;
    }

    // only one ingredient for now
    @Override
    public List<Recipe> listRecipeGivenIngredient(String ingredient) {
        String queryStr = "SELECT r FROM Recipe r";
        TypedQuery<Recipe> query = entityManager.createQuery(queryStr, Recipe.class);
        List<Recipe> recipes = query.getResultList();

        // Filter recipes to only include those containing the specified ingredient
        List<Recipe> recipeWithIngredient = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (isIngredientPresent(ingredient, recipe.getIngredients())) {
                recipeWithIngredient.add(recipe);
            }
        }
        return recipeWithIngredient;
    }
}
