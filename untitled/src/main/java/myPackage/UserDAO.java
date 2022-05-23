package myPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class UserDAO {

 public UserDAO (LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
     this.entityManagerFactoryBean = entityManagerFactoryBean;
 }
        private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

//    @Autowired
//    void setEntityManagerFactoryBean(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
//        this.entityManagerFactoryBean = entityManagerFactoryBean;
//    }
        private EntityManagerFactory entityManagerFactory = entityManagerFactoryBean.getObject();


        public List<User> getAllUsers() {

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            List<User> users = entityManager.createQuery("from User", User.class).getResultList();

            return users;
        }


        public void saveUser(User user) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.merge(user);
        }


        public User getUserById(long id) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            return entityManager.find(User.class, id);
        }


        public void deleteUser(long id) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.remove(entityManager.find(User.class, id));
        }
}
