package myPackage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
//
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = context.getBean(LocalContainerEntityManagerFactoryBean.class);
//
        System.out.println(entityManagerFactoryBean);

//        UserDAO userDAO = new UserDAO(entityManagerFactoryBean);
//
//
//        User user1 = new User();
//        user1.setName("Vasya");
//        user1.setLastName("Pupkin");
//        user1.setEmail("VaskaPupkin@mail.com");
//
//        User user2 = new User();
//        user2.setName("Sasha");
//        user2.setLastName("Ivanova");
//        user2.setEmail("Sasha@mail.com");
//
//        userDAO.saveUser(user1);
//        userDAO.saveUser(user2);
//
//        userDAO.getAllUsers().forEach(usr -> System.out.println(usr.toString()));
//
//        System.out.println(userDAO.getUserById(1).toString());
//
//        userDAO.deleteUser(1);
//
//        userDAO.getAllUsers().forEach(usr -> System.out.println(usr.toString()));
//
   }
}
