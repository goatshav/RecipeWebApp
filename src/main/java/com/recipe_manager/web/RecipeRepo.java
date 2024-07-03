package com.recipe_manager.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class RecipeRepo {
    
    private static List<Recipe> recipes = new ArrayList<>();
    private static int recipeCount;

    static {
        recipes.add(new Recipe(++recipeCount, "Omelette", "breakfast", new ArrayList<>(List.of("eggs", "onions", "peppers", "salt", "cheese")), new ArrayList<>(List.of("omelette step 1", "omelette step 2", "omeletee step 3"))));
        recipes.add(new Recipe(++recipeCount, "Burritos", "lunch", new ArrayList<>(List.of("tomatoes", "onions", "avocadoes", "lime", "beans", "tortillas")), new ArrayList<>(List.of("burritos step 1", "burritos step 2"))));
        recipes.add(new Recipe(++recipeCount, "Tofu Noodles", "dinner", new ArrayList<>(List.of("tofu", "cornflour", "noodles", "broccoli", "garlic")), new ArrayList<>(List.of("noodles step 1", "noodles step 2", "noodles step 3"))));
    }

    public List<Recipe> listAllRecipes() {
        return recipes;
    }

    public Recipe listSpecificRecipe(int id) {
        for (Recipe recipe : recipes) {
            if (id == recipe.getId()) {
                return recipe;
            }
        }
        return null;
    }

    // only one ingredient for now
    public List<Recipe> listRecipeGivenIngredient(String ingredient) {
        List<Recipe> recipewithIngredient = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getIngredients().contains(ingredient)) {
                recipewithIngredient.add(recipe);
            }
        }
        return recipewithIngredient;
    }

    public void addNewRecipe(Recipe recipe) {
        recipe.setId(++recipeCount);
        recipes.add(recipe);
    }

    public void deleteById(int id) {
        Iterator<Recipe> iterator = recipes.iterator();
        while (iterator.hasNext()) {
            Recipe recipe = iterator.next();
            if (recipe.getId() == id) {
                iterator.remove();
                break;
            }
        }
    }
}
