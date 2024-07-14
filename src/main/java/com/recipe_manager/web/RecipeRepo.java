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
        recipes.add(new Recipe(++recipeCount, "omelette", "breakfast", "eggs,onions,cheese", "omelette step 1,omelette step 2", 3.5));
        recipes.add(new Recipe(++recipeCount, "noodles", "lunch", "noodles,sauce", "noodles step 1,noodles step 2", 4.1));
        recipes.add(new Recipe(++recipeCount, "pizza", "dinner", "pizza", "pizza step 1 pizza step 2", 4.7));
    }

    // reader actions:

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
    public List<Recipe> listRecipeGivenIngredient(String ingredient) {
        List<Recipe> recipewithIngredient = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (isIngredientPresent(ingredient, recipe.getIngredients())) {
                recipewithIngredient.add(recipe);
            }
        }
        return recipewithIngredient;
    }

    // writer actions:

    public void addNewRecipe(Recipe recipe) {
        recipe.setId(++recipeCount);
        recipes.add(recipe);
    }

    public void updateRecipe(Recipe recipe) {
        for (Recipe r : recipes) {
            if (r.getId() == recipe.getId()) {
                recipes.set(r.getId()-1, recipe);
            }
        }
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
