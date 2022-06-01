package spring_mvc_hibernate.service;

import spring_mvc_hibernate.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public void saveUser(User user);

    public User getUserById(long id);

    void deleteUser(long id);
}
