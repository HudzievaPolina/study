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
    public List<User> getAllUsers() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<User> users = entityManager.createQuery("from User", User.class).getResultList();

        return users;
    }

    @Override
    public void saveUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.merge(user);
    }

    @Override
    public User getUserById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.remove(entityManager.find(User.class, id));
    }
}
