package com.recipe_manager.web;

import java.util.ArrayList;

public class Recipe {
    
    private int id;
    private String title;
    private String category;
    private ArrayList<String> ingredients;
    private ArrayList<String> steps;
    
    public Recipe(int id, String title, String category, ArrayList<String> ingredients, ArrayList<String> steps) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    @Override
    public String toString() {
        return "Recipe [id=" + id + ", title=" + title + ", category=" + category + ", ingredients=" + ingredients
                + ", steps=" + steps + "]";
    }

    

    

}
