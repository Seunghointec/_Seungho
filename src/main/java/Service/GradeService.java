package Service;

import Entities.Exam;
import Entities.Grade;
import Entities.Person;
import Entities.User;
import Repositiory.ExamDAO;
import Repositiory.GradeDAO;
import Repositiory.UserDAO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;


public class GradeService {
        private Scanner keyboard;
        private UserDAO userDAO;
        private ExamDAO examDAO;
        private GradeDAO gradeDAO;


    public GradeService() {
        keyboard = new Scanner(System.in);
        userDAO = new UserDAO();
        examDAO = new ExamDAO();
        gradeDAO = new GradeDAO();
    }

    //SUPER HARD
    //De datum staat automatisch op vandaag
    //de grade mag niet minder dan 0 zijn, en mag niet meer zijn dan de punten van het examen
    //Je gaat een Exam moeten oproepen van de bestaande lijst van examens, Examen mag dus niet op null staan
    //Je gaat een Person moeten terugvinden met User
    public void createGrade(User user) {
        Grade grade = enterGrade(user);



        gradeDAO.createGrade(grade);
    }

    //EASY
    public void getOneGradeById(){
        System.out.println("Please enter the id of the grade you would like to see");
        int idOfGrade = keyboard.nextInt();
        gradeDAO.getGradeById(idOfGrade);
    }

    //MEDIUM
    //Controleer eerst of de user niet 'null' is
    //Gebruik een user.getPerson methode, en maak een extra methode in je DAO/repository om resultaten op te vragen met person
    public void getAllGradeByPerson(User user){

        System.out.println("Please enter the user whom you like to see all grades for");
        String login = keyboard.next();
        user = userDAO.getUserById(login);
        if (user == null) {
            System.out.println("THere is no such an user");
        } else {
            gradeDAO.getAllGradesPerson(user.getPerson()).forEach(System.out::println);
        }
    }

    //HARD
    //Controleer eerst of de user niet 'null' is
    //vraag alle grades op van een Person en kies de Grade die je wilt aanpassen
    //Enkel de gradeValue en de comment mogen aangepast worden
    //de grade mag niet minder dan 0 zijn, en mag niet meer zijn dan de punten van het examen
    public void updateGrade(User user){
        Grade grade;
        BigDecimal inputGrade;
        Exam exam = new Exam();

        System.out.println("Please enter the user whom you like to see all grades for");
        String login = keyboard.next();
        user = userDAO.getUserById(login);
        if (user == null) {
            System.out.println("There is no such an user");
        } else {
            getAllGradeByPerson(user);
        }

        System.out.println("Please enter the id of the grade you would like to update");
        int idOfGrade = keyboard.nextInt();
        grade = gradeDAO.getGradeById(idOfGrade);


        System.out.println("Please enter new grade");
        int newGrade= keyboard.nextInt();
        if (newGrade < 0 && newGrade > exam.getTotal()) {
            System.out.println("This entry is an invalid grade");
        } else {
            grade.setGradeValue(BigDecimal.valueOf(newGrade));
        }

        System.out.println("please change the comment of this grade");
        String answer = keyboard.next();
        grade.setComment(answer);

        gradeDAO.updateGrade(grade);
    }

    //EASY
    //Controleer eerst of de user niet 'null' is
    //vraag alle grades op van een Person en kies de Grade die je wilt aanpassen
    public void deleteGrade(User user){
        Grade grade;
        System.out.println("Please enter the user that you would like to delete grade for");
        String login = keyboard.next();
        user = userDAO.getUserById(login);
        if (user == null) {
            System.out.println("There is no such an user");
        } else {
            getAllGradeByPerson(user);
        }
        System.out.println("Please enter the grade of the id that you would like to delete");
        grade = gradeDAO.getGradeById(keyboard.nextInt());
        gradeDAO.deleteGrade(grade);
    }

    //----
    //extra private methodes hieronder
    private Exam chooseExam() {
        examDAO.getAllExams().forEach(System.out::println);
        System.out.println("Please enter the id of the exam that you would like to enter the grade for");
        int idOfExam = keyboard.nextInt();
        Exam examId = examDAO.getExamById((long) idOfExam);
        if (examId == null || idOfExam < 0) {
            System.out.println("this option is invalid");
        }
        return examId;
    }

    private Grade enterGrade(User user) {
        Grade grade = new Grade();
        Exam exam = chooseExam();
        System.out.println("Which of the person would you like to enter the grade for?");
        String answer = keyboard.next();
        grade.setPerson(user.getPerson());
        if (user.getLogin().equals(answer)) {
            System.out.println("please enter your grade for this exam");
            int inputGrade = keyboard.nextInt();
            grade = gradeDAO.getGradeById(inputGrade);
            grade.setExam(exam);
            if (inputGrade < 0 && inputGrade > exam.getTotal()) {
                System.out.println("This entry is an invalid grade");
            } else {
                grade.setGradeValue(BigDecimal.valueOf(inputGrade));
            }
            grade.setDate(LocalDate.now());
        }
        return grade;
    }
}





   /*      Grade grade1 = new Grade (personDAO.getPersonById (personId), examDAO.getExamById ((long) examId),
         grade,comment,internalComment,absent,postponed,dateTime);
         gradeDAO.addGrade (grade1);
*/