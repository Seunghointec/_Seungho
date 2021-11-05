package Repositiory;

import Entities.Person;

import javax.persistence.EntityManager;
import java.util.List;

public class PersonDAO {

    public Person getPersonById(Integer id) {//id wrapped class
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        Person person = em.find(Person.class, id);
        return person;
    }

    public List<Person> getAllPersons() {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        List<Person> persons = em.createQuery("SELECT pe FROM Person pe").getResultList();
        return persons;
    }

    public void createPerson(Person person) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
    }

    public void updatePerson(Person person) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        //Person personToUpdate = em.find(Person.class, person.getId());
        // this line will again ask for same person id not update
        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();
    }

    public void deletePerson (Person person) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        Person personToDelete = em.find(Person.class, person.getId());
        em.getTransaction().begin();
        em.remove(personToDelete);
        em.getTransaction().commit();
    }
}
