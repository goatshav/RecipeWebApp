package com.recipe_manager.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class CreateAccountController {
    
    @Autowired
    private UserJPARepo userRepo;

    public CreateAccountController(UserJPARepo userRepo) {
        this.userRepo = userRepo;
    }

    // for testing
    @GetMapping("/get-all-users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // need to add authentication

    @PostMapping("/create-account")
    public User createUserAccount(@RequestBody User user) {
        userRepo.save(user);

        return user;
    }

}
