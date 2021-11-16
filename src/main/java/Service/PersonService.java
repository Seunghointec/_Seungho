package Service;

import Entities.Gender;
import Entities.Person;
import Entities.User;
import Repositiory.PersonDAO;
import Repositiory.UserDAO;

import java.util.Locale;
import java.util.Scanner;

public class PersonService {

    private PersonDAO personDAO;
    private Scanner keyboard;

    public PersonService() {
        personDAO = new PersonDAO();
        keyboard = new Scanner(System.in);
    }

    public void getPerson(Person person) {
        System.out.println("please enter the id of the person");
        int idOfPerson = keyboard.nextInt ();
    }

    public void updatePerson(Person person) {
        System.out.println("Do you want to change first name of the user? Y/N");
        String answer = keyboard.nextLine();
        switch(answer.toLowerCase(Locale.ROOT)) {
            case "Y":
                person.setFirstName(answer);
                System.out.println("your first name is changed");
                break;
            case "N":
                System.out.println("Okay, good luck with your studies");
                break;
            default:
                System.out.println("That's incorrect options");
        }

        System.out.println("Do you want to change last name of the user? Yes or No");
        answer = keyboard.nextLine();
        switch(answer) {
            case "yes":
                person.setLastName(answer);
                System.out.println("Congratulation! Your last name is changed");
                break;
            case "no":
                System.out.println("Well done, You are who you are");
                break;
            default:
                System.out.println("That's incorrect options");
        }

        System.out.println("Do you want to change the gender of the user? Y/N");
        answer = keyboard.next();
        switch(answer.toLowerCase(Locale.ROOT)) {
            case "Y":
                System.out.println("Please choose your gender \n1: Male \n2: Female \n0: Other");
                int choice = keyboard.nextInt();
                switch (choice) {
                    case 1:
                        person.setGender(Gender.MALE);
                        break;
                    case 2:
                        person.setGender(Gender.FEMALE);
                        break;
                    default:
                        person.setGender(Gender.OTHERS);
                        break;
                }
                System.out.println("Congratulation! Your gender has been changed");
                break;
            case "N":
                System.out.println("Well done, You are who you are");
                break;
            default:
                System.out.println("That's an incorrect option");
        }
        personDAO.updatePerson(person);
    }

    public void deletePerson(Person person) {
        int idOfPerson =0;

        System.out.println("Please enter the id of the person who you wish to delete");
        idOfPerson = Integer.parseInt(keyboard.nextLine());
        person = personDAO.getPersonById(idOfPerson);

        System.out.println("Are you sure you want to delete this person? Y/N");
        String answer = keyboard.nextLine();
        switch(answer.toLowerCase(Locale.ROOT)) {
            case "Y":
                personDAO.deletePerson(person);
                System.out.println("This person has been deleted");
                break;
            case "N":
                System.out.println("Okay, that's a safe choice for now");
                break;
            default:
                System.out.println("That's incorrect options");
        }
    }
}
