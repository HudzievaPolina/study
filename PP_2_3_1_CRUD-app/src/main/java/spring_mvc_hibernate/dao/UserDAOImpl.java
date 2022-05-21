package spring_mvc_hibernate.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring_mvc_hibernate.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    @Transactional
    public List<User> getAllUsers() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<User> users = entityManager.createQuery("from User", User.class).getResultList();

        return users;
    }
}
