package Repositiory;

import Entities.Exam;

import javax.persistence.EntityManager;
import java.util.List;

public class ExamDAO {

    public Exam getExamById (Long id) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        Exam exam = em.find(Exam.class, id);
        return exam;
    }

    public List<Exam> getAllExams() {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        List<Exam> exams = em.createQuery("SELECT ex FROM Exam ex").getResultList();
        return exams;
    }

    public void createExam (Exam exam) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(exam);
        em.getTransaction().commit();
    }

    public void updateExam (Exam exam) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.merge(exam);
        em.getTransaction().commit();
    }

    public void deleteExam (Exam exam) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        Exam examToDelete = em.find(Exam.class, exam.getId());
        em.getTransaction().begin();
        em.remove(examToDelete);
        em.getTransaction().commit();
    }
}
