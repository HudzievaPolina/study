package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    public SessionFactory factory = Util.getSessionFactory();

    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users "
                    + "(id INT(5) NOT NULL AUTO_INCREMENT, "
                    + "name VARCHAR(50), "
                    + "lastName VARCHAR(50), "
                    + "age INT(3), "
                    + "PRIMARY KEY (id));");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS Users;");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query query = session.createSQLQuery("INSERT INTO Users (name, lastName, age) "
                                            + "VALUES (?, ?, ?);");
            query.setParameter(1, name);
            query.setParameter(2, lastName);
            query.setParameter(3, age);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
            Transaction transaction = null;
            try (Session session = factory.openSession()) {
                transaction = session.getTransaction();
                transaction.begin();
                Query query = session.createSQLQuery("DELETE FROM Users WHERE id = ?;");
                query.setParameter(1, id);
                query.executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            List<User> list = session.createSQLQuery("SELECT * FROM Users;").addEntity(User.class).list();
            transaction.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
        Query query = session.createSQLQuery("DELETE FROM Users;");
        query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
}
