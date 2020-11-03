/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.ProjectQuiz.service.impl;

import com.mrd.ProjectQuiz.model.Answer;
import com.mrd.ProjectQuiz.repository.AnswerRepository;
import com.mrd.ProjectQuiz.repository.custom.AnswerRepositoryCustom;
import com.mrd.ProjectQuiz.service.AnswerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerRepositoryCustom answerRepositoryCustom;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, AnswerRepositoryCustom answerRepositoryCustom) {
        this.answerRepository = answerRepository;
        this.answerRepositoryCustom = answerRepositoryCustom;
    }

    @Override
    public Answer findById(Long id) {
        return answerRepositoryCustom.findById(id);
    }

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public void delete(Answer answer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existCorrectByIdQuizIsTrue(Long id) {
        return answerRepositoryCustom.existCorrectByIdQuizIsTrue(id);
    }

}
