/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ace.recipe_sharing.service;

import com.ace.recipe_sharing.model.Recipe;
import com.ace.recipe_sharing.model.User;
import java.util.List;

/**
 *
 * @author adyar
 */
public interface RecipeService {
    
    public Recipe createRecipe(Recipe recipe, User user);
    
    public Recipe findRecipeById(Long id) throws Exception;
    
    public void deleteRecipe(Long id) throws Exception;
    
    public Recipe updateRecipe(Recipe recipe, Long Id) throws Exception;
    
    public List<Recipe>findAllRecipe();
    
    public Recipe likeRecipe(Long recipeId, User user) throws Exception;
}
