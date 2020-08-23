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
    private String active;
    private String isauthority;

    public Role() {
    }

    public Role(Integer idrole, String role, String active, String isauthority) {
        this.idrole = idrole;
        this.role = role;
        this.active = active;
        this.isauthority = isauthority;
    }

    public String getIsauthority() {
        return isauthority;
    }

    public void setIsauthority(String isauthority) {
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    
    

}
