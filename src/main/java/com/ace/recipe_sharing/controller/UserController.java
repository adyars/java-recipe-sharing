/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ace.recipe_sharing.controller;

import com.ace.recipe_sharing.model.User;
import com.ace.recipe_sharing.repository.UserRepository;
import com.ace.recipe_sharing.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author adyar
 */
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/api/user/profile")
    public User findUserByJwt(@RequestHeader("Authorization") String jwt ) throws Exception{
        
        User user = userService.findUserByJwt(jwt);
        
        return user;
        
    }

}
