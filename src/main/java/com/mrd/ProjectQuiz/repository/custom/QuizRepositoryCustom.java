/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.ProjectQuiz.repository.custom;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mrd.ProjectQuiz.model.Quiz;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
@Repository
public class QuizRepositoryCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuizRepositoryCustom.class);

    private SessionFactory sessionFactory;

    @Autowired
    public QuizRepositoryCustom(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }

        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public List<Quiz> findByLevel(int level) {
        Session session = openSession();
        List<Quiz> lstLvl = null;
        try {
            Query query = session.createNativeQuery("SELECT * FROM quiz WHERE level = ?", Quiz.class);
            query.setParameter(1, level);
            lstLvl = query.getResultList();
        } catch (NoResultException ex) {
            LOGGER.error(ex.toString());
        } finally {
            closeSession(session);
        }
        
        return lstLvl;
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
