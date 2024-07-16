package com.recipe_manager.web.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeJPARepo extends JpaRepository<Recipe, Integer>, RecipeRepoCustom {

} 
