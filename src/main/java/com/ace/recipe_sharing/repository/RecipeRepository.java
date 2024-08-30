/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ace.recipe_sharing.repository;

import com.ace.recipe_sharing.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author adyar
 */
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    
}
