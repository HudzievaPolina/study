package com.example._3_1_1_crud_springboot.dao;

import com.example._3_1_1_crud_springboot.entity.User;
import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(long id);

    void deleteUser(long id);
}
