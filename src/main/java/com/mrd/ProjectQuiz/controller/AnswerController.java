/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.ProjectQuiz.controller;

import com.mrd.ProjectQuiz.model.Answer;
import com.mrd.ProjectQuiz.model.Quiz;
import com.mrd.ProjectQuiz.service.AnswerService;
import com.mrd.ProjectQuiz.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
public class AnswerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    AnswerService answerService;

    @Autowired
    QuizService quizService;

    @RequestMapping(value = "/api/answer", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Answer answer) {
        if (quizService.findById(answer.getQuiz_id()) == null) {
            LOGGER.info("quiz_id is not exist ...");
            return new ResponseEntity<>("not created", HttpStatus.BAD_REQUEST);
        }

        Quiz quiz = quizService.findById(answer.getQuiz_id());
        String s = quiz.getQuiz_type();
        /*
        check loai cau hoi:  
            - quiz_type = M => chi insert dc 1 dap an la dung 
            - quiz_type = N => insert dc nhieu dap an la dung
            - quiz_type = D => chi insert dc 1 dap an duy nhat cho ID nay (loai dien cau tra loi)  
         */
        if (answer.getCorrect()== true && s.equals("M")) {            
            if(answerService.existCorrectByIdQuizIsTrue(answer.getQuiz_id())){ //đã tồn tại đáp án đúng cho quiz_type= 'M'
                LOGGER.info("Answer correct for quiz_id is exist ...");
                return new ResponseEntity<>("Answer correct for quiz_id is exist", HttpStatus.BAD_REQUEST);
            }
        } else if (quiz.getQuiz_type().equals("N")) {
              ;
        } else {
              ;
        }

        answerService.save(answer);
        return new ResponseEntity<>(answer, HttpStatus.CREATED);

    }
}
