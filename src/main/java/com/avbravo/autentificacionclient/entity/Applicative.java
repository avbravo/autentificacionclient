/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Secondary;

/**
 *
 * @author avbravo
 */
public class Applicative {

    @Id
    private Integer idapplicative;
    @Secondary
    private String applicative;
    private String path;
    private String image;
    private String description;
    private String active;
    private String shortname;
  
  
    public Applicative() {
    }

    public Applicative(Integer idapplicative, String applicative, String path, String image, String description, String active, String shortname) {
        this.idapplicative = idapplicative;
        this.applicative = applicative;
        this.path = path;
        this.image = image;
        this.description = description;
        this.active = active;
        this.shortname = shortname;
    }

    public Integer getIdapplicative() {
        return idapplicative;
    }

    public void setIdapplicative(Integer idapplicative) {
        this.idapplicative = idapplicative;
    }

    public String getApplicative() {
        return applicative;
    }

    public void setApplicative(String applicative) {
        this.applicative = applicative;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

   
@Override
    public boolean equals(Object object) {
        if (!(object instanceof Applicative)) {
            return false;
        }
       Applicative other = (Applicative) object;
        if ((this.idapplicative == null && other.idapplicative != null) || (this.idapplicative != null && !this.idapplicative.equals(other.idapplicative))) {
            return false;
        }
        return true;
    }
 
  
   
  

}
