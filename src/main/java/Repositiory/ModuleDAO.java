package Repositiory;

import Entities.Module;


import javax.persistence.EntityManager;
import java.util.List;

public class ModuleDAO {

    public Module getModuleById(Long id) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        Module module= em.find(Module.class, id);
        return module;
    }

    public List<Module> getAllModules() {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        List<Module> modules = em.createQuery("SELECT mo FROM Module mo").getResultList();
        return modules;
    }

    public void createModule(Module module) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(module);
        em.getTransaction().commit();
    }

    public void updateModule(Module module) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.merge(module);
        em.getTransaction().commit();
    }

    public void deleteModule(Module module) {
        EntityManager em = ConnectionEMF.getEMF().createEntityManager();
        Module moduleToDelete = em.find(Module.class, module.getId());
        em.getTransaction().begin();
        em.remove(moduleToDelete);
        em.getTransaction().commit();
    }
}
