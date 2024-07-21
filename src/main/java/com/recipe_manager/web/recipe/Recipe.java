package com.recipe_manager.web.recipe;

import com.recipe_manager.web.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Recipe {
    
    protected Recipe() {
        
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private User writer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically generates an id/primary key val
    private int id;

    @Size(min=1)
    private String title;

    @Size(min=1)
    private String category;

    @Size(min=1)
    private String ingredients;

    @Size(min=1)
    private String steps;

    private double rating;
    
    public Recipe(int id, String title, String category, String ingredients, String steps, double rating) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.ingredients = ingredients;
        this.steps = steps;
        this.rating = rating;
    }

    public User getWriter() {
        return writer;
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

    public String getIngredients() {
        return ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Recipe [author=" + writer + ", id=" + id + ", title=" + title + ", category=" + category
                + ", ingredients=" + ingredients + ", steps=" + steps + ", rating=" + rating + "]";
    }

}
