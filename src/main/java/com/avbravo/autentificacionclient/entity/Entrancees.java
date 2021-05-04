/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Id;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author avbravo
 */
public class Entrancees {
    @Id
    private Integer identrancees;
    private Date date;
    private Integer idprofile;
    private Integer idapplicativa;
    private Integer idrole;
    private Integer iddepartament;

    public Entrancees() {
    }

    public Entrancees(Integer identrancees, Date date, Integer idprofile, Integer idapplicativa, Integer idrole, Integer iddepartament) {
        this.identrancees = identrancees;
        this.date = date;
        this.idprofile = idprofile;
        this.idapplicativa = idapplicativa;
        this.idrole = idrole;
        this.iddepartament = iddepartament;
    }

    public Integer getIdentrancees() {
        return identrancees;
    }

    public void setIdentrancees(Integer identrancees) {
        this.identrancees = identrancees;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIdprofile() {
        return idprofile;
    }

    public void setIdprofile(Integer idprofile) {
        this.idprofile = idprofile;
    }

    public Integer getIdapplicativa() {
        return idapplicativa;
    }

    public void setIdapplicativa(Integer idapplicativa) {
        this.idapplicativa = idapplicativa;
    }

    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    public Integer getIddepartament() {
        return iddepartament;
    }

    public void setIddepartament(Integer iddepartament) {
        this.iddepartament = iddepartament;
    }

   
 @Override
    public boolean equals(Object object) {
        if (!(object instanceof Entrancees)) {
            return false;
        }
      Entrancees other = (Entrancees) object;
        if ((this.identrancees == null && other.identrancees != null) || (this.identrancees != null && !this.identrancees.equals(other.identrancees))) {
            return false;
        }
 return EqualsBuilder.reflectionEquals(this, object);
    }
   
    
}
