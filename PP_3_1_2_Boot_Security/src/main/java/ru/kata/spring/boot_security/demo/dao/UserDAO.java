package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(long id);

    void deleteUser(long id);
}
