/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.ProjectQuiz.service;

import java.util.List;
import com.mrd.ProjectQuiz.model.User;

/**
 *
 * @author Admin
 */
public interface UserService {

    List<User> findAll();

    User findById(Long id);

    void save(User user);

    void delete(User user);

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
