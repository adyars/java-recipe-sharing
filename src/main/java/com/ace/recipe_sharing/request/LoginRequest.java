/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ace.recipe_sharing.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author adyar
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    
    private String email;
    private String password;
    
}
