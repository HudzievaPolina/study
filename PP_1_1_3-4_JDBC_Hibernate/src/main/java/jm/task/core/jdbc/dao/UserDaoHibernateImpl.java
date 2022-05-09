package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    public Session session = Util.getSessionFactory().openSession();

    @Override
    public void createUsersTable() {
        session.beginTransaction();
        Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users "
                                    + "(id INT(5) NOT NULL AUTO_INCREMENT, "
                                    + "name VARCHAR(50), "
                                    + "lastName VARCHAR(50), "
                                    + "age INT(3), "
                                    +  "PRIMARY KEY (id));");
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        session.beginTransaction();
        Query query = session.createSQLQuery("DROP TABLE IF EXISTS Users;");
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session.beginTransaction();
        Query query = session.createSQLQuery("INSERT INTO Users (name, lastName, age) "
                                            + "VALUES (?, ?, ?);");
        query.setParameter(1, name);
        query.setParameter(2, lastName);
        query.setParameter(3, age);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        session.beginTransaction();
        Query query = session.createSQLQuery("DELETE FROM Users WHERE id = ?;");
        query.setParameter(1, id);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        session.beginTransaction();
        List<User> list = session.createSQLQuery("SELECT * FROM Users;").addEntity(User.class).list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        session.beginTransaction();
        Query query = session.createSQLQuery("DELETE FROM Users;");
        query.executeUpdate();
        session.getTransaction().commit();
    }
}
