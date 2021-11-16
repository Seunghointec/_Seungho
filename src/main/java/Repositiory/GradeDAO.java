package Repositiory;

import Entities.Course;
import Entities.Grade;
import Entities.Person;
import Entities.User;

import javax.persistence.EntityManager;
import java.util.List;

public class GradeDAO {

    public Grade getGradeById(int id) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        Grade grade = em.find(Grade.class, id);
        return grade;
    }

    public List<Grade> getAllGrades() {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        List<Grade> grades = em.createQuery("Select gr From Grade gr").getResultList();
        return grades;
    }

    public List<Grade> getAllGradesPerson(Person person) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        List<Grade> grades = em.createQuery("Select gr From Grade gr WHERE gr.person=:person").setParameter("person",person).getResultList();
        return grades;
    }

    public void createGrade(Grade grade) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(grade);
        em.getTransaction().commit();
    }

    public void updateGrade(Grade grade) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.merge(grade);
        em.getTransaction().commit();
    }

    public void deleteGrade(Grade grade) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        Grade gradeToDelete = em.find(Grade.class, grade.getId());
        em.getTransaction().begin();
        em.remove(gradeToDelete);
        em.getTransaction().commit();
    }
}
