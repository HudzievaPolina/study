package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ray", "Bradbury", (byte) 56);
        System.out.println("User с именем Ray добавлен в базу данных");
        userService.saveUser("Franz", "Kafka", (byte) 25);
        System.out.println("User с именем Franz добавлен в базу данных");
        userService.saveUser("Alexander", "Pushkin", (byte) 19);
        System.out.println("User с именем Alexander добавлен в базу данных");
        userService.saveUser("Fyodor", "Dostoevsky", (byte) 48);
        System.out.println("User с именем Fyodor добавлен в базу данных");

        userService.getAllUsers().forEach(user -> System.out.println(user.toString()));
        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.closeConnection();
    }
}
