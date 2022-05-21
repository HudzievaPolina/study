package spring_mvc_hibernate.dao;

import spring_mvc_hibernate.entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();
}
