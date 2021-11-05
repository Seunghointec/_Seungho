package Repositiory;

import Entities.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDAO {

    public User getUserById(String login) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        User user = em.find(User.class, login);
        return user;
    }

    public List<User> getAllUsers() {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        List<User> users = em.createQuery("SELECT us FROM User us").getResultList();
        return users;
    }

    public void createUser(User user) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public void updateUser(User user) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public void deleteUser(User user) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        User userToDelete = em.find(User.class, user.getLogin());
        em.getTransaction().begin();
        em.remove(userToDelete);
        em.getTransaction().commit();
    }
}
