/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bhaduri.jogajog.DA;

import javax.persistence.EntityManagerFactory;
import org.bhaduri.jogajog.JPA.MinutedataJpaController;

/**
 *
 * @author susmita
 */
public class MinutedataDA extends MinutedataJpaController{
    
    public MinutedataDA(EntityManagerFactory emf) {
        super(emf);
    }
    
}