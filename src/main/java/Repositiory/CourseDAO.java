package Repositiory;

import Entities.Course;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CourseDAO {

    public Course getCourseByID(Long id) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        Course course = em.find(Course.class, id);
        return course;
    }

    public List<Course> getAllCourses() {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        List<Course> courses = em.createQuery("Select mf From Course mf").getResultList();
        return courses;
    }

    public void createCourse (Course course) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
    }

    public void updateCourse (Course course) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.merge(course);
        em.getTransaction().commit();
    }

    public void deleteCourse (Course course) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        Course courseToDelete = em.find(Course.class, course.getId());
        em.getTransaction().begin();
        em.remove(courseToDelete);
        em.getTransaction().commit();
    }
}
