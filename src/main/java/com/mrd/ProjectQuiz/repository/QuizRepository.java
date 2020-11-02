/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.ProjectQuiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mrd.ProjectQuiz.model.Quiz;
/**
 *
 * @author Admin
 */
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    
}
