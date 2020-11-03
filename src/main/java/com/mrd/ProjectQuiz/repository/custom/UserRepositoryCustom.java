/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.ProjectQuiz.repository.custom;

import com.mrd.ProjectQuiz.model.User;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
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
public class UserRepositoryCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuizRepositoryCustom.class);

    private SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryCustom(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }

        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public User findById(Long id) {
        Session session = openSession();
        User user =null;
        try {
            Query query = session.createNativeQuery("SELECT * FROM quiz WHERE id = ?", User.class);
            query.setParameter(1, id);
            user = (User) query.getSingleResult();
        } catch (EntityNotFoundException ex) {
            LOGGER.error(ex.toString());
        } finally {
            closeSession(session);
        }
        return user;
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
