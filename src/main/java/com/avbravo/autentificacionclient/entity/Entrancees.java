/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Id;
import java.util.Date;

/**
 *
 * @author avbravo
 */
public class Entrancees {
    @Id
    private Integer identrancees;
    private Date date;
    private Integer idprofile;

    public Entrancees() {
    }

    public Entrancees(Integer identrancees, Date date, Integer idprofile) {
        this.identrancees = identrancees;
        this.date = date;
        this.idprofile = idprofile;
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

   
    
}
