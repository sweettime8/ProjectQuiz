/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.ProjectQuiz.service;

import java.util.List;
import com.mrd.ProjectQuiz.model.Quiz;

/**
 *
 * @author Admin
 */
public interface QuizService {

    Quiz findById(Long id);

    List<Quiz> findByLevel(int level);

    List<Quiz> findAll();

    void save(Quiz quiz);

    void delete(Quiz quiz);
}
