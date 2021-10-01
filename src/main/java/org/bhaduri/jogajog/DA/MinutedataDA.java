/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bhaduri.jogajog.DA;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import org.bhaduri.jogajog.JPA.MinutedataJpaController;
import org.bhaduri.jogajog.entities.Minutedata;
import org.bhaduri.nouka.DTO.ListScripData;
import org.bhaduri.nouka.DTO.ScripData;

/**
 *
 * @author susmita
 */
public class MinutedataDA extends MinutedataJpaController{
    
    public MinutedataDA(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Minutedata> findByScripid(String scripid) {
        EntityManager em = getEntityManager();
        TypedQuery<Minutedata> query = em.createNamedQuery("Minutedata.findByScripid", Minutedata.class);
        query.setParameter("scripid", scripid);        
        List<Minutedata> listofscripdata = query.getResultList();
        return listofscripdata;
    }
    
}
