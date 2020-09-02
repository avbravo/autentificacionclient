/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import java.util.Date;

/**
 *
 * @author avbravo
 *
 */
public class Otp {
@Id
    private Integer idotp;
 
@Referenced(collection = "User",
            field = "iduser", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.UsertRepository")
    private User user;


    private Date expiry;
    
    private String used;
 
    public Otp() {
    }

    public Otp(Integer idotp, User user, Date expiry, String used) {
        this.idotp = idotp;
        this.user = user;
        this.expiry = expiry;
        this.used = used;
    }

   

    public Integer getIdotp() {
        return idotp;
    }

    public void setIdotp(Integer idotp) {
        this.idotp = idotp;
    }

   
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }
 @Override
    public boolean equals(Object object) {
        if (!(object instanceof Otp)) {
            return false;
        }
       Otp other = (Otp) object;
        if ((this.idotp== null && other.idotp != null) || (this.idotp != null && !this.idotp.equals(other.idotp))) {
            return false;
        }
        return true;
    }

   
  
  
}
