/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ace.recipe_sharing.controller;

import com.ace.recipe_sharing.model.Recipe;
import com.ace.recipe_sharing.model.User;
import com.ace.recipe_sharing.service.RecipeService;
import com.ace.recipe_sharing.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author adyar
 */
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    
    @Autowired
    private RecipeService recipeService;
    
    @Autowired
    private UserService userService;
    
    @PostMapping("")
    public Recipe createdRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) throws Exception{
        
        User user = userService.findUserByJwt(jwt);
        
        Recipe createdRecipe = recipeService.createRecipe(recipe, user);
        return createdRecipe;
    }
    
    @GetMapping()
    public List<Recipe> getAllRecipe() throws Exception{
        
        List<Recipe> recipes = recipeService.findAllRecipe();
        return recipes;
    }
    
    
    @DeleteMapping("/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws Exception{
        
        recipeService.deleteRecipe(recipeId);
        return "Recipe deleted succesfully";
    }
    
    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception{
                
        Recipe updatedRecipe = recipeService.updateRecipe(recipe, id);
        return updatedRecipe;
    }
    
    @PutMapping("/{id}/like")
    public Recipe likeRecipe(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception{
        
       User user = userService.findUserByJwt(jwt);
        
        Recipe likedRecipe = recipeService.likeRecipe(id, user);
        return likedRecipe;
    }


}
