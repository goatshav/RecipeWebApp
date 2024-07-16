package com.recipe_manager.web.recipe;

import java.util.List;

public interface RecipeRepoCustom {

    List<Recipe> listRecipeGivenIngredient(String ingredient);
}
