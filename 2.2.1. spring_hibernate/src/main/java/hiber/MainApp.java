package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car carBMW = new Car("BMW");
        Car carVolkswagen = new Car("Volkswagen");
        Car carAudi = new Car("Audi");
        Car carRR = new Car("Range Rover");

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        user1.setCar(carBMW);
        carBMW.setUser(user1);
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        user2.setCar(carVolkswagen);
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        user3.setCar(carAudi);
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        user4.setCar(carRR);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(user.getCar());
            System.out.println();
        }

        User findUser = userService.getUserOnCarModelAndSeries("Range Rover", 4);
        System.out.println("This find user: " + findUser);

        context.close();
    }
}
