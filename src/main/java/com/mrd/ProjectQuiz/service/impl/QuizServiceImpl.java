/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.ProjectQuiz.service.impl;

import com.mrd.ProjectQuiz.model.Quiz;
import com.mrd.ProjectQuiz.repository.QuizRepository;
import com.mrd.ProjectQuiz.repository.custom.QuizRepositoryCustom;
import com.mrd.ProjectQuiz.service.QuizService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class QuizServiceImpl implements QuizService{
    
    private final QuizRepository quizRepository;
    private final QuizRepositoryCustom quizRepositoryCustom;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository, QuizRepositoryCustom quizRepositoryCustom) {
        this.quizRepository = quizRepository;
        this.quizRepositoryCustom = quizRepositoryCustom;
    }
    
    @Override
    public Quiz findById(Long id) {
        Quiz quiz = quizRepositoryCustom.findById(id);
        return quiz;
    }

    @Override
    public List<Quiz> findByLevel(int level) {
        return quizRepositoryCustom.findByLevel(level);
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public void save(Quiz quiz) {
        quizRepository.save(quiz);
    }

    @Override
    public void delete(Quiz quiz) {
        quizRepository.delete(quiz);
    }
    
}
