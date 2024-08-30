/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ace.recipe_sharing.service;

import com.ace.recipe_sharing.config.JwtProvider;
import com.ace.recipe_sharing.model.User;
import com.ace.recipe_sharing.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author adyar
 */
@Service
public class UserServiceImplementation implements UserService{
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws Exception {
        
        Optional<User> opt = userRepository.findById(userId);
        
        if(opt.isPresent()){
            return opt.get();
        }
        
        throw new Exception("User not found with Id " + userId);
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        
        if(email==null){
            throw new Exception("Provide valid jwt token");
        }
        
        User user = userRepository.findByEmail(email);
        
        if(user==null){
            throw new Exception("User not found with email " + email);
        }
        
        return user;
    }
}
