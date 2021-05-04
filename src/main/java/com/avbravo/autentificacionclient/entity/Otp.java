/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;

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
            repository = "com.avbravo.autentificacion.repository.UserRepository")
    private User user;
    
    private Integer otp;

    private Date expirationdate;

    private Boolean used;
    private Boolean isexpired;

    public Otp() {
    }

    public Otp(Integer idotp, User user, Integer otp, Date expirationdate, Boolean used, Boolean isexpired) {
        this.idotp = idotp;
        this.user = user;
        this.otp = otp;
        this.expirationdate = expirationdate;
        this.used = used;
        this.isexpired = isexpired;
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

    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    public Boolean getIsexpired() {
        return isexpired;
    }

    public void setIsexpired(Boolean isexpired) {
        this.isexpired = isexpired;
    }

   
    
    
    

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }
    
    
    

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Otp)) {
            return false;
        }
        Otp other = (Otp) object;
        if ((this.idotp == null && other.idotp != null) || (this.idotp != null && !this.idotp.equals(other.idotp))) {
            return false;
        }
 return true;
    }
    
    public boolean equalsReflection(Object object) {
        if (!(object instanceof Otp)) {
            return false;
        }
        Otp other = (Otp) object;
        if ((this.idotp == null && other.idotp != null) || (this.idotp != null && !this.idotp.equals(other.idotp))) {
            return false;
        }
 return EqualsBuilder.reflectionEquals(this, object);
    }

}
