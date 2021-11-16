package Service;

import Entities.Exam;
import Repositiory.ExamDAO;


import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class ExamService {

    private ExamDAO examDAO;
    private Scanner keyboard = new Scanner(System.in);
    private int idOfExam;
    private boolean isRightInput =false;

    public ExamService() throws SQLException {
        examDAO = new ExamDAO();
    }

    public void outputExamById() {

        do {
            System.out.println("Please enter the ID of the exam, you would like to enter");
            try {
                idOfExam = Integer.parseInt(keyboard.next());
                keyboard.nextLine();
                isRightInput= true;
            } catch (NumberFormatException e) {
                isRightInput= false;
                System.out.println("Id is not correct");
            }
        }while (!isRightInput);

        Exam exam = examDAO.getExamById((long) idOfExam);
        if (exam!= null) System.out.println(exam);
        else System.out.println("This Id is not present");
    }

    public void UpdateExam() {
        do {
            System.out.println("Please enter the ID of the exam, you would like to enter");
            try {
                idOfExam = Integer.parseInt(keyboard.next());
                keyboard.nextLine();
                isRightInput= true;
            } catch (NumberFormatException e) {
                isRightInput= false;
                System.out.println("Id is not correct");
            }
        }while (!isRightInput);

        Exam exam = examDAO.getExamById((long) idOfExam);
        if (exam!= null) {
            System.out.println(exam);
        }
        else {
            System.out.println("This Id is not present");
        }

        System.out.println("Would you like to change the name of the exam? Y or N");
        String answer = keyboard.nextLine();

        switch (answer.toLowerCase(Locale.ROOT)) {
                case "Y":
                    exam.setName(answer);
                    break;
                case "N":
                    System.out.println("okay");
                    break;
                default:

        }

        System.out.println("would you like to change the descriptions of this exam? Y or N");
        answer = keyboard.nextLine();

        switch (answer.toLowerCase(Locale.ROOT)) {
            case "Y":
                exam.setDescription(answer);
                break;
            case "N":
                System.out.println("okay");
                break;
            default:
        }
    }
}
