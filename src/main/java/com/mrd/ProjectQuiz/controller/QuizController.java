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
import com.mrd.ProjectQuiz.model.Quiz;
import com.mrd.ProjectQuiz.service.QuizService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;


/**
 *
 * @author Admin
 */
@RestController
public class QuizController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    QuizService quizService;

    @RequestMapping(value = "/api/quiz", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Quiz quiz) {
        quizService.save(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/quiz/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getQuizById(@PathVariable("id") Long id) {
        Quiz quiz = quizService.findById(id);
        return new ResponseEntity<>(quiz, HttpStatus.CREATED);
    }
    
    //trả về danh sách câu hỏi show ra màn hình choi (3 câu lv1, 2 câu lv2, 1 câu lv3) total socre = 10
    @RequestMapping(value = "/api/quiz", method = RequestMethod.GET)
    public ResponseEntity<List<Quiz>> getAllQuiz() {
        List<Quiz> lstQuizLv1 = quizService.findByLevel(1);
        List<Quiz> lstQuizLv2 = quizService.findByLevel(2);
        List<Quiz> lstQuizLv3 = quizService.findByLevel(3);
        
        //tron cau hoi
        Collections.shuffle(lstQuizLv1);
        Collections.shuffle(lstQuizLv2);
        Collections.shuffle(lstQuizLv3);

        List<Quiz> lstQuiz = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            lstQuiz.add(lstQuizLv1.get(i));
        }
        for (int i = 0; i < 2; i++) {
            lstQuiz.add(lstQuizLv2.get(i));
        }
        for (int i = 0; i < 1; i++) {
            lstQuiz.add(lstQuizLv3.get(i));
        }

        return new ResponseEntity<>(lstQuiz, HttpStatus.CREATED);
    }

}
