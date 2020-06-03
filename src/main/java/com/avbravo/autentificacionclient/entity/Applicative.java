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
    private String active;

    private String path;
    private String image;
    private String descroption;

    public Applicative() {
    }

    public Applicative(Integer idapplicative, String applicative, String active, String path, String image, String descroption) {
        this.idapplicative = idapplicative;
        this.applicative = applicative;
        this.active = active;
        this.path = path;
        this.image = image;
        this.descroption = descroption;
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

    public String getDescroption() {
        return descroption;
    }

    public void setDescroption(String descroption) {
        this.descroption = descroption;
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

}
