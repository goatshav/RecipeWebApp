package com.recipe_manager.web;

import java.util.ArrayList;

import jakarta.validation.constraints.Size;

public class Recipe {
    
    private int id;

    @Size(min=1)
    private String title;

    @Size(min=1)
    private String category;

    @Size(min=1)
    private ArrayList<String> ingredients;

    @Size(min=1)
    private ArrayList<String> steps;

    private double rating;
    
    public Recipe(int id, String title, String category, ArrayList<String> ingredients, ArrayList<String> steps, double rating) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.ingredients = ingredients;
        this.steps = steps;
        this.rating = rating;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Recipe [id=" + id + ", title=" + title + ", category=" + category + ", ingredients=" + ingredients
                + ", steps=" + steps + ", rating=" + rating + "]";
    }

    

    

    

}
