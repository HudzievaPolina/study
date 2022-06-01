package com.example._3_1_1_crud_springboot.service;



import com.example._3_1_1_crud_springboot.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public void saveUser(User user);

    public User getUserById(long id);

    void deleteUser(long id);
}
