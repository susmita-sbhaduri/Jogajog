/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bhaduri.jogajog.JPA;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.bhaduri.jogajog.JPA.exceptions.NonexistentEntityException;
import org.bhaduri.jogajog.JPA.exceptions.PreexistingEntityException;
import org.bhaduri.jogajog.entities.Scrips;

/**
 *
 * @author susmita
 */
public class ScripsJpaController implements Serializable {

    public ScripsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Scrips scrips) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(scrips);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findScrips(scrips.getScripid()) != null) {
                throw new PreexistingEntityException("Scrips " + scrips + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Scrips scrips) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            scrips = em.merge(scrips);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = scrips.getScripid();
                if (findScrips(id) == null) {
                    throw new NonexistentEntityException("The scrips with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Scrips scrips;
            try {
                scrips = em.getReference(Scrips.class, id);
                scrips.getScripid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The scrips with id " + id + " no longer exists.", enfe);
            }
            em.remove(scrips);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Scrips> findScripsEntities() {
        return findScripsEntities(true, -1, -1);
    }

    public List<Scrips> findScripsEntities(int maxResults, int firstResult) {
        return findScripsEntities(false, maxResults, firstResult);
    }

    private List<Scrips> findScripsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Scrips.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Scrips findScrips(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Scrips.class, id);
        } finally {
            em.close();
        }
    }

    public int getScripsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Scrips> rt = cq.from(Scrips.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
