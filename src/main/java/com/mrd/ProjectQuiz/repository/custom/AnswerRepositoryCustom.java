/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.ProjectQuiz.repository.custom;

import com.mrd.ProjectQuiz.model.Answer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class AnswerRepositoryCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuizRepositoryCustom.class);

    private SessionFactory sessionFactory;

    @Autowired
    public AnswerRepositoryCustom(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }

        this.sessionFactory = factory.unwrap(SessionFactory.class);
    } 

    public Answer findById(Long id) {
        Session session = openSession();
        Answer answer = null;
        try {
            Query query = session.createNativeQuery("SELECT * FROM answer WHERE id = ?", Answer.class);
            query.setParameter(1, id);
            answer = (Answer) query.getSingleResult();
        } catch (EntityNotFoundException ex) {
            LOGGER.error(ex.toString());
        } finally {
            closeSession(session);
        }
        return answer;
    }
    /*
        check dap an correct = true is ton tai cho id quizz (Quiz_tye = 'M'
        da ton tai : ko dc add them dap an dung nua
    */
    public boolean existCorrectByIdQuizIsTrue(Long quiz_id) { 
        Session session = openSession();
        List<Answer> lstAns = null;
        try {
            Query query = session.createNativeQuery("SELECT * FROM answer WHERE quiz_id = ?", Answer.class);
            query.setParameter(1, quiz_id);
            lstAns = query.getResultList();
            
            for(Answer ans : lstAns){
                if(ans.getCorrect() == true){
                    return true;
                }
            }
            
        } catch (NoResultException ex) {
            LOGGER.error(ex.toString());
        } finally {
            closeSession(session);
        }

        return false;
    }

    private Session openSession() {
        Session session = this.sessionFactory.openSession();
        return session;
    }

    private void closeSession(Session session) {
        if (session.isOpen()) {
            session.disconnect();
            session.close();
        }
    }
}
