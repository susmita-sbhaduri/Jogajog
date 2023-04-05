/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bhaduri.jogajog.DA;

import javax.persistence.EntityManagerFactory;
import org.bhaduri.jogajog.JPA.ScripsJpaController;

/**
 *
 * @author susmita
 */
public class ScripsDA extends ScripsJpaController{
    
    public ScripsDA(EntityManagerFactory emf) {
        super(emf);
    }
    
}
