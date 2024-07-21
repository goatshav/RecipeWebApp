package com.recipe_manager.web.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recipe_manager.web.recipe.Recipe;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details")
public class User {

    protected User() {

    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   // @Column(name = "isWriter")
    private boolean isWriter;

    @Size(min=1)
    private String username;

    @Size(min=1)
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "writer")
    private List<Recipe> recipes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isWriter() {
        return isWriter;
    }

    public void setIsWriter(boolean author) {
        this.isWriter = author;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", author=" + isWriter + ", username=" + username + ", password=" + password
                + ", recipes=" + recipes + "]";
    }
    
}
