/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Secondary;
import com.avbravo.jmoordb.pojos.UserInfo;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class Role {

     @Id
    private Integer idrole;
    @Secondary
    private String role;
    private Boolean active;

    private Boolean isauthority;
    private Boolean ishumanresourcesauthority;

    public Role() {
    }

    public Role(Integer idrole, String role, Boolean active, Boolean isauthority, Boolean ishumanresourcesauthority) {
        this.idrole = idrole;
        this.role = role;
        this.active = active;
        this.isauthority = isauthority;
        this.ishumanresourcesauthority = ishumanresourcesauthority;
    }

    
    
    public Boolean getIshumanresourcesauthority() {
        return ishumanresourcesauthority;
    }

    public void setIshumanresourcesauthority(Boolean ishumanresourcesauthority) {
        this.ishumanresourcesauthority = ishumanresourcesauthority;
    }

   
    

    public Boolean getIsauthority() {
        return isauthority;
    }

    public void setIsauthority(Boolean isauthority) {
        this.isauthority = isauthority;
    }

  

    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

   

     @Override
    public boolean equals(Object object) {
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.idrole == null && other.idrole != null) || (this.idrole != null && !this.idrole.equals(other.idrole))) {
            return false;
        }
        return true;
    }

}
