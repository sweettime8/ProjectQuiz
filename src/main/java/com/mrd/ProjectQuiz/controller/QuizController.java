/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.ProjectQuiz.controller;

import com.mrd.ProjectQuiz.repository.custom.QuizRepositoryCustom;
import com.mrd.ProjectQuiz.repository.QuizRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mrd.ProjectQuiz.model.Quiz;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Admin
 */
@RestController
public class QuizController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizRepositoryCustom quizRepositoryCustom;

    @RequestMapping(value = "/api/quiz", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Quiz quiz) {
        quizRepository.save(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.CREATED);
    }

    //trả về danh sách câu hỏi show ra màn hình (1 câu lv1, 1 câu lv2, 1 câu lv3)
    @RequestMapping(value = "/api/quiz", method = RequestMethod.GET)
    public ResponseEntity<List<Quiz>> getAllUser() {
        List<Quiz> lstQuizLv1 = quizRepositoryCustom.findByLevel(1);
        List<Quiz> lstQuizLv2 = quizRepositoryCustom.findByLevel(2);
        List<Quiz> lstQuizLv3 = quizRepositoryCustom.findByLevel(3);
        
        //tron cau hoi
        Collections.shuffle(lstQuizLv1);
        Collections.shuffle(lstQuizLv2);
        Collections.shuffle(lstQuizLv3);

        List<Quiz> lstQuiz = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            lstQuiz.add(lstQuizLv1.get(i));
            lstQuiz.add(lstQuizLv2.get(i));
            lstQuiz.add(lstQuizLv3.get(i));
        }

        return new ResponseEntity<>(lstQuiz, HttpStatus.CREATED);
    }

}
