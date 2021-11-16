package Service;

import Entities.User;
import Repositiory.UserDAO;


import java.util.Scanner;

public class LoginRequest {

    public void run(User user) {

        UserDAO userDAO = new UserDAO();

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please enter your username");
        String inpUser = keyboard.nextLine();
        if (userDAO.getUserById(inpUser) == null) { // to test if user does exist
            System.out.println("No valid account");
        } else {
            user = userDAO.getUserById(inpUser);
            System.out.println("Please enter your password");
            String inpPass = keyboard.nextLine(); // gets input from user
            if (inpPass.equals(user.getPasswordHash())) {
                if (user.isActive()) {
                    System.out.println("welcome " + user.getPerson().getFirstName());
                    System.out.println("your encrypt password is " + user.getEncryptedPassword());
                } else
                    System.out.println("you are not an active user");
            }else
                System.out.println("You have entered a wrong password");
        }
    }
}