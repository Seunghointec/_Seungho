package Service;

import Entities.Gender;
import Entities.Person;
import Entities.User;
import Repositiory.PersonDAO;
import Repositiory.UserDAO;

import java.util.Scanner;


public class UserService {

    private UserDAO userDAO;//created userDAO to have an interactive session between frontend and backend
    private PersonDAO personDAO;
    private Scanner keyboard;
    private User user;
    private Person person;
    private PersonService personService;

    public UserService() {
        userDAO = new UserDAO();
        personDAO = new PersonDAO();
        keyboard = new Scanner(System.in);
        user = new User();
        person = new Person();
        personService = new PersonService();
    }

    //HARD
    //1.Zorg ervoor dat een persoon ook aangemaakt wordt
    //2.Maak een controle methode die vraagt om een gebruiker een passwoord twee keer in te geven
    //3. Het wachtwoord wordt hier geincrypteerd
    public void createUser(){
        System.out.println("Please create yourself as a user");
        System.out.println("Please enter your first name");

        person.setFirstName(keyboard.next());
        System.out.println("Please enter your last name");
        person.setLastName(keyboard.next());

        System.out.println("Please choose your gender \n1: Male \n2: Female \n0: Other");
        int choice = keyboard.nextInt();

        switch (choice) {
            case 1 : person.setGender(Gender.MALE); break;
            case 2 : person.setGender(Gender.FEMALE); break;
            default : person.setGender(Gender.OTHERS); break;
        }

        System.out.println("Please create your user login");
        user.setLogin(keyboard.next());
        user.setPasswordHash(checkPassword());
        user.setActive(true);
        user.setPerson(person);
        user.encrypt(user.getPasswordHash());
        userDAO.createUser(user);
    }

    //EASY
    //Als er geen User terug gegeven wordt, stuur als bericht "User does not exist"
    public User getOneUserByName(){ //Om een User te vinden, pakken we de User op ID
        System.out.println("Please enter your user login");
        user = userDAO.getUserById(keyboard.nextLine());
        if (user == null){
            System.out.println("User does not exist");
        } else if (user.isActive()) {
            System.out.println("welcome " + user.getPerson().getFirstName());
        }
        else {
            System.out.println("you are not an active user");
        }
        return user;
    }

    //EASY
    //print een lijst uit van alle users.
    public void getAllUsers(){
        userDAO.getAllUsers().forEach(System.out::println);
    }

    //EASY
    //Een username mag niet aangepast worden
    //Bonus HARD
    //De Person hoef je niet te updaten (als je dat wilt, doe je dat best via een aparte personservice,
    // via een aprte updatePersonMethode)
    public void updateUser(){

        userDAO.getAllUsers().forEach(System.out::println);
        System.out.println("Please enter the login of the person you would like to update");
        user = userDAO.getUserById(keyboard.nextLine());

        System.out.println("Do you want to change the password of the user? Yes or No");
        String answer = keyboard.nextLine();


        switch(answer) {
            case "Yes":
                user.setPasswordHash(checkPassword());
                System.out.println("your password is changed");
                break;
            case "No":
                System.out.println("Okay, good luck with your life");
                break;
            default:
                System.out.println("That's incorrect options");
        }

        System.out.println("Do you also want to change the details of the person? Yes or No");
        answer = keyboard.nextLine();

        switch(answer) {
            case "Yes":
                personService.updatePerson(user.getPerson());
                System.out.println("The person details has been updated");
                break;
            case "No":
                System.out.println("Okay, good luck with your life");
                break;
            default:
                System.out.println("That's incorrect options");
        }
        userDAO.updateUser(user);
    }

    //MEDIUM
    //Vraag de User een passwoord in te geven voor dat hij zijn account kan verwijderen.
    //De Person moet ook mee gedeleted worden
    public void deleteUser(){

        userDAO.getAllUsers().forEach(System.out::println);
        System.out.println("Please enter the login of the person you would like to delete");
        user = userDAO.getUserById(keyboard.nextLine());
        checkPassword();
        userDAO.deleteUser(user);
        personService.deletePerson(user.getPerson());
    }

    //----
    //extra private methodes hieronder

    private String checkPassword() {
        boolean samePassword = false;
        String password = null;

        while(!samePassword) { //continuous loop if password fails to match
            System.out.println("please enter your password");
            password = keyboard.next();
            System.out.println("please enter your password again");
            String confirmPassword = keyboard.next();

            if (password.equals(confirmPassword)) {
                samePassword = true;
            } else
                System.out.println("The password does not match");
        }
        return password;
    }

}
