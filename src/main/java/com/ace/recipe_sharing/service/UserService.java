/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ace.recipe_sharing.service;

import com.ace.recipe_sharing.model.User;

/**
 *
 * @author adyar
 */
public interface UserService {
    
    public User findUserById(Long userId) throws Exception;
    
    public User findUserByJwt(String jwt) throws Exception;
    
}
