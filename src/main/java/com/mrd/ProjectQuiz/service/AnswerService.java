/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.ProjectQuiz.service;

import com.mrd.ProjectQuiz.model.Answer;
import java.util.List;
/**
 *
 * @author Admin
 */
public interface AnswerService {

    Answer findById(Long id);

    List<Answer> findAll();

    void save(Answer answer);

    void delete(Answer answer);
    
    boolean existCorrectByIdQuizIsTrue(Long id);
}
