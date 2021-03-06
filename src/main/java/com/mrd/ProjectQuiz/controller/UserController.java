/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.ProjectQuiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mrd.ProjectQuiz.model.User;
import com.mrd.ProjectQuiz.service.UserService;
import java.util.List;

/**
 *
 * @author Admin
 */
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser() {
        List<User> lstUser = userService.findAll();       
        return new ResponseEntity<>(lstUser, HttpStatus.CREATED);      
    }
    
    @RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            LOGGER.info("username is exist !");
            return new ResponseEntity<>("username is exist !", HttpStatus.BAD_REQUEST);
        } 
        if(userService.existsByEmail(user.getEmail())){
            LOGGER.info("email is exist !");
            return new ResponseEntity<>("email is exist !", HttpStatus.BAD_REQUEST);
        }
        
        userService.save(user);
      
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
    

}
