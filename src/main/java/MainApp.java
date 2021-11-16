import Entities.*;
import Entities.Module;
import Repositiory.*;
import Service.AttributeEncryptor;
import Service.GradeService;
import Service.LoginRequest;
import Service.UserService;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;



public class MainApp {

    private static int choiceOne = 9;
    private static int choiceTwo = 9;
    private static boolean continueThis = true;
    private static User user;
    private static Scanner scanner;
    private static GradeService gradeService;
    private static UserService userService;

    public MainApp() {
        scanner = new Scanner(System.in);
        gradeService = new GradeService();
        userService = new UserService();
    }

    public static void main(String[] args) {


        try {
            while (continueThis) {
                getChoice();
                choices();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getChoice() {

        Scanner scanner = new Scanner(System.in);

        while (choiceOne == 9) {
            System.out.println("What do you want to look at? \n1: Users \n2: Grades\n0: End");
            choiceOne = scanner.nextInt();
            if (choiceOne == 0) break;
            if (choiceOne < 1 || choiceOne > 2) {
                choiceOne = 9;
                System.out.println("Invalid choice.");
            } else {
                while (choiceTwo == 9) {
                    System.out.println("What do you want to look at? \n1: See All \n2: See One \n3: Add One \n4: Edit One \n5: Delete One\n0: End");
                    choiceTwo = scanner.nextInt();
                    if (choiceTwo == 0) break;
                    if (choiceTwo < 1 || choiceTwo > 5) {
                        choiceTwo = 9;
                        System.out.println("Invalid choice.");
                    }
                }
            }
        }
    }

    private static void choices() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        GradeService gradeService = new GradeService();
        UserService userService = new UserService();
        if (choiceTwo != 0)
            if (choiceOne == 1) {

                switch (choiceTwo) {
                    case 1:
                        userService.getAllUsers();
                        break;//see All
                    case 2:
                        user = userService.getOneUserByName();
                        break;//see
                    case 3:
                        userService.createUser();
                        break;//add One
                    case 4:
                        userService.updateUser();
                        break;//edit One
                    case 5:
                        userService.deleteUser();
                        break;//delete One
                }
                System.out.println("We did a user thing!");


            } else if (choiceOne == 2) {
                switch (choiceTwo) {
                    case 1:
                        gradeService.getAllGradeByPerson(user);
                        break;//see All
                    case 2:
                        gradeService.getOneGradeById();
                        break;//see One
                    case 3:
                        gradeService.createGrade(user);
                        break;//add One
                    case 4:
                        gradeService.updateGrade(user);
                        break;//edit One
                    case 5:
                        gradeService.deleteGrade(user);
                        break;//delete One
                }
                System.out.println("We did a grade thing!");

            }
        choiceOne = 9;
        choiceTwo = 9;
        boolean goodAnswer;
        do {
            System.out.println("Do you want to Try again? Y/N");
            String answer = scanner.next();
            if (answer.toUpperCase(Locale.ROOT).equals("N")) {
                System.out.println("Bye!");
                continueThis = false;
                break;
            }
            if (!answer.toUpperCase(Locale.ROOT).equals("Y")) {
                goodAnswer = false;
                System.out.println(answer + " is not a good answer.");
            } else goodAnswer = true;
        } while (!goodAnswer);

    }
}


















 /*CourseDAO courseDAO = new CourseDAO();
 Course course = new Course("Biologie hoger", "Biologie veld",
                "BIO200", "Http://www.boo.com/3", true);

        Course course1 = new Course("Chemistry", "Chemistry Field",
                "CHEM100", "Http://www.boo.com/2", false);

       courseDAO.createCourse(course1);
        courseDAO.updateCourse(course);
        Course course2 = courseDAO.getCourseByID(11L); //new object instantiated, recalled ID
        courseDAO.deleteCourse(course2); //delete new instantiated object that has been called by ID
        System.out.println(courseDAO.getAllCourses());*/

//-----------------------------------------------------------------------

        /*PersonDAO personDAO = new PersonDAO();


        System.out.println(person);

        Person person4 = personDAO.getPersonById(33);  //referring to old object by ID
        Course course2 = courseDAO.getCourseByID(6L);   //referring to old object by ID
        person4.setCourse(course2); //set the course for person4 (but actually Sam)
        personDAO.updatePerson(person4);
        System.out.println(personDAO.getAllPersons());*/

//------------------------------------------------------------------------


        /*
         UserDAO userDAO = new UserDAO();
        User user = new User("jsmith1", "niej2914", true);
        User user1 = new User("shoffman2", "shoffman6783", false);

        Person person2 = new Person("Sam", "Hoffman", Gender.FEMALE, course);
        Person person = new Person("John", "Smith", Gender.MALE, course1);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.run(user);
        userDAO.createUser(user);
        userDAO.createUser(user1);

        user2.setEncryptedPassword(user.encrypt("niej2914"));
        User user2 = userDAO.getUserById("shoffman2");
        userDAO.updateUser(user2);
        userDAO.deleteUser(user2);
        System.out.println(userDAO.getAllUsers());*/

//-------------------------------------------------------------------------

        /*ModuleDAO moduleDAO = new ModuleDAO();

        Module module = new Module("Biochemie",
                "Leren over metabolisme en de celenergie", course1);
        Module module2 = new Module("Organische chemie",
                "Leer nieuwe verbinding te synthetiseren", course1);
        moduleDAO.createModule(module);
        moduleDAO.createModule(module2);
        module.setCourse(course1);
        module2.setCourse(course1);
        moduleDAO.updateModule(module);
        moduleDAO.updateModule(module2);*/
        /*Module module3 = moduleDAO.getModuleById(1L);
        moduleDAO.deleteModule(module3);
        System.out.println(moduleDAO.getAllModules());



        //module.setExams(exams);
        moduleDAO.createModule(module);*/



//--------------------------------------------------------------------------

        /*ExamDAO examDAO = new ExamDAO();
        //exams.add(new Exam("Bio200 Exam", "An intermediate level exam",
                  //      LocalDate.of(2022, 9, 22), 9, 80));

        Exam exam = new Exam("Bio200 Exam", "An entry exam for the first semester",
                LocalDate.of(2021,9,21), 80, 100);

        Exam exam2 = new Exam("BioChem200 Exam", "A second year exam",
                LocalDate.of(2022, 1,3), 60, 100);

        Exam exam3 = examDAO.getExamById(49L);
        exam3.setName("Bio200");
        exam3.setDescription("a intermediate level for the freshman");
        exam3.setModule(module2);
        examDAO.updateExam(exam3);

        Exam exam4 = examDAO.getExamById(50L);
        exam4.setModule(module);
        //exam4.setExamGroup("biochem500");



        examDAO.updateExam(exam4);


        //Remember! everytime we created object, we have to refer back to the first instantiation
        //if we want to update the first instantiation object.

         /*examDAO.createExam(exam);
        examDAO.createExam(exam2);

        Exam exam3 = examDAO.getExamById(35L);
        examDAO.deleteExam(exam3);
        //examDAO.updateExam(exam3);
        //examDAO.updateExam(exam2);
        //examDAO.getExamById(8L);*/

//----------------------------------------------------------------------------















