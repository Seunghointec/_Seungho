import Entities.*;
import Entities.Module;
import Repositiory.*;
import Service.AttributeEncryptor;
import Service.LoginRequest;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MainApp {
    public static void main(String[] args) {


        CourseDAO courseDAO = new CourseDAO();
        Course course = new Course("Biologie hoger", "Biologie veld",
                "BIO200", "Http://www.boo.com/3", true);

        Course course1 = new Course("Chemistry", "Chemistry Field",
                "CHEM100", "Http://www.boo.com/2", false);

        /*courseDAO.createCourse(course1);
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

        ModuleDAO moduleDAO = new ModuleDAO();

        /*moduleDAO.createModule(module);
        moduleDAO.createModule(module2);
        module.setCourse(course1);
        module2.setCourse(course1);
        moduleDAO.updateModule(module);
        moduleDAO.updateModule(module2);*/
        /*Module module3 = moduleDAO.getModuleById(1L);
        moduleDAO.deleteModule(module3);*/
        System.out.println(moduleDAO.getAllModules());

        List<Exam> exams = new ArrayList<>(); //could initialize new exams by arraylist

        exams.add(new Exam("Bio200 Exam", "An intermediate level exam",
                LocalDate.of(2022, 9, 22), 9, 80));


        Exam exam = new Exam("Bio100 Exam", "An entry exam for the first semester",
                LocalDate.of(2021,9,21), 80, 100);

        Exam exam2 = new Exam("BioChem200 Exam", "A second year exam",
                LocalDate.of(2022, 1,3), 60, 100);
        exams.add(exam);
        exams.add(exam2);

        Module module = new Module("Biochemie",
                "Leren over metabolisme en de celenergie", course1);
        Module module2 = new Module("Organische chemie",
                "Leer nieuwe verbinding te synthetiseren", course1);

        module.setExams(exams);
        moduleDAO.createModule(module);


            //--------------------------------------------------------------------------

        /*ExamDAO examDAO = new ExamDAO();

        //Remember! everytime we created object, we have to refer back to the first instantiation
        //if we want to update the first instantiation object.

        examDAO.createExam(exam);
        examDAO.createExam(exam2);
        /*
        Exam exam3 = examDAO.getExamById(35L);
        examDAO.deleteExam(exam3);
        //examDAO.updateExam(exam3);
        //examDAO.updateExam(exam2);
        //examDAO.getExamById(8L);

        for (Exam getAllExam : exams) {
            System.out.println(getAllExam);
        }
        try {

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionEMF.getEMF().close();
        }*/



            //----------------------------------------------------------------------------

    }
}















