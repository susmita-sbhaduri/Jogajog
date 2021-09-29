/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bhaduri.jogajog.DA;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import org.bhaduri.jogajog.JPA.MinutedataJpaController;

/**
 *
 * @author susmita
 */
public class MinutedataDA extends MinutedataJpaController{
    
    public MinutedataDA(EntityManagerFactory emf) {
        super(emf);
    }
    
     public List<String> getScripId() {
        EntityManager em = getEntityManager();
        TypedQuery<String> query = em.createNamedQuery("tarangdb.scripID", String.class);
        
        List<String> scripids = query.getResultList();
        return scripids;
    }
    
}
