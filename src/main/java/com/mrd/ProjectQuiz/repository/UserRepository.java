/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.ProjectQuiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mrd.ProjectQuiz.model.User;

/**
 *
 * @author Admin
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
