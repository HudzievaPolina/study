import org.springframework.beans.factory.annotation.Autowired;

public class Main {
    public static void main(String[] args) {


        UserDAO userDAO = new UserDAO();

        User user1 = new User();
        user1.setName("Vasya");
        user1.setLastName("Pupkin");
        user1.setEmail("VaskaPupkin@mail.com");

        User user2 = new User();
        user2.setName("Sasha");
        user2.setLastName("Ivanova");
        user2.setEmail("Sasha@mail.com");

        userDAO.saveUser(user1);
        userDAO.saveUser(user2);

        userDAO.getAllUsers().forEach(usr -> System.out.println(usr.toString()));

        System.out.println(userDAO.getUserById(1).toString());

        userDAO.deleteUser(1);

        userDAO.getAllUsers().forEach(usr -> System.out.println(usr.toString()));

    }
}
