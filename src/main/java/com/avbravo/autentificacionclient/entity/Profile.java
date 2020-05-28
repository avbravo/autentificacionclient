/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import java.util.List;

/**
 *
 * @author avbravo Los campos enteros pueden ser con un autoincrementable o
 * insertando la fechahoradelsistema en milisegundos.
 */
public class Profile {

    @Id
    private Integer idprofile;

    @Referenced(collection = "Applicative",
            field = "idapplicative", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.ApplicativeRepository")
    Applicative applicative;
    @Referenced(collection = "Role",
            field = "idrole", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.RoleRepository")
    Role role;

    private String active;
      
    public Profile() {
    }

    public Profile(Integer idprofile, Applicative applicative, Role role, String active) {
        this.idprofile = idprofile;
        this.applicative = applicative;
        this.role = role;
        this.active = active;
    }

    public Integer getIdprofile() {
        return idprofile;
    }

    public void setIdprofile(Integer idprofile) {
        this.idprofile = idprofile;
    }

    public Applicative getApplicative() {
        return applicative;
    }

    public void setApplicative(Applicative applicative) {
        this.applicative = applicative;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }



   

  

}
