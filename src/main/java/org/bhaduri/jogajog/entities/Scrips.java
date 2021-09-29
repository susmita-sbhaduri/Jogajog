/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bhaduri.jogajog.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author susmita
 */
@Entity
@Table(name = "scrips")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Scrips.findAll", query = "SELECT s FROM Scrips s"),
    @NamedQuery(name = "Scrips.findByScripid", query = "SELECT s FROM Scrips s WHERE s.scripid = :scripid")})
public class Scrips implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "scripid")
    private String scripid;

    public Scrips() {
    }

    public Scrips(String scripid) {
        this.scripid = scripid;
    }

    public String getScripid() {
        return scripid;
    }

    public void setScripid(String scripid) {
        this.scripid = scripid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scripid != null ? scripid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scrips)) {
            return false;
        }
        Scrips other = (Scrips) object;
        if ((this.scripid == null && other.scripid != null) || (this.scripid != null && !this.scripid.equals(other.scripid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.bhaduri.jogajog.entities.Scrips[ scripid=" + scripid + " ]";
    }
    
}
