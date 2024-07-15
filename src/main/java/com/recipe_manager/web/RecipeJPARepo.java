package com.recipe_manager.web;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeJPARepo extends JpaRepository<Recipe, Integer>, RecipeRepoCustom {

} 
