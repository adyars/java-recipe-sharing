/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ace.recipe_sharing.controller;

import com.ace.recipe_sharing.config.JwtProvider;
import com.ace.recipe_sharing.model.User;
import com.ace.recipe_sharing.repository.UserRepository;
import com.ace.recipe_sharing.request.LoginRequest;
import com.ace.recipe_sharing.response.AuthResponse;
import com.ace.recipe_sharing.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author adyar
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CustomUserDetailsService customUserDetails;
    
    @Autowired
    private JwtProvider jwtProvider;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception{
        
        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        
        User isExistEmail = userRepository.findByEmail(email);
        if(isExistEmail != null){
            throw new Exception("Email is used by another account..");
        }
        
        User createUser = new User();
        createUser.setEmail(email);
        createUser.setPassword(passwordEncoder.encode(password));
        createUser.setFullName(fullName);       
        userRepository.save(createUser);
        
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String token = jwtProvider.generateToken(authentication);
        
        AuthResponse res = new AuthResponse();
        
        res.setJwt(token);
        res.setMessage("Sign up success");
        
        return res;
    }
    
    @PostMapping("/signin")
    public AuthResponse signinHandle(@RequestBody LoginRequest loginRequest){
        
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        
        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String token = jwtProvider.generateToken(authentication);
        
        AuthResponse res = new AuthResponse();
        
        res.setJwt(token);
        res.setMessage("Sign In success");
        
        return res;
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username); 
        System.out.println("this is encoded password: " + userDetails.getPassword() );
        System.out.println("this is password: " + password);
     
        if(userDetails==null){
            throw new BadCredentialsException("User not found");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }
    
        return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
    }
    
}
