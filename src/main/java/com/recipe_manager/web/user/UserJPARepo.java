package com.recipe_manager.web.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepo extends JpaRepository<User, Integer> {

}