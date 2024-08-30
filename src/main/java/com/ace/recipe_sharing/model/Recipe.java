/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ace.recipe_sharing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author adyar
 */

@Getter
@Setter
@Entity
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    
    private String title;
    
    @ManyToOne
    private User user;
    
    private String image;
    private String description;
    private boolean vegetarian;
    private LocalDateTime createdAt;
    private List<Long> likes = new ArrayList<>();
}
