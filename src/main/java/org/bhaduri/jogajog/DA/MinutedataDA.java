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
import org.bhaduri.nouka.DTO.ScripData;

/**
 *
 * @author susmita
 */
public class MinutedataDA extends MinutedataJpaController{
    
    public MinutedataDA(EntityManagerFactory emf) {
        super(emf);
    }
    
     public List<ScripData> findByScripid(String scripid) {
        EntityManager em = getEntityManager();
        TypedQuery<ScripData> query = em.createNamedQuery("Minutedata.findByScripid", ScripData.class);
        query.setParameter("scripid", scripid);
        List<ScripData> mindatalistperscrip = query.getResultList();
        return mindatalistperscrip;
    }
    
}
