/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Ignore;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author avbravo Los campos enteros pueden ser con un autoincrementable o
 * insertando la fechahoradelsistema en milisegundos.
 */
public class Profile {

    @Id
    private Integer idprofile;
    private Integer idapplicative;
    private Integer idrole;
    private Integer iddepartament;

    private Boolean active;
    @Ignore
    Applicative applicative;
    @Ignore
    Role role;
    @Ignore
    Departament departament;

    public Profile() {
    }

    public Profile(Integer idprofile, Integer idapplicative, Integer idrole, Integer iddepartament, Boolean active, Applicative applicative, Role role, Departament departament) {
        this.idprofile = idprofile;
        this.idapplicative = idapplicative;
        this.idrole = idrole;
        this.iddepartament = iddepartament;
        this.active = active;
        this.applicative = applicative;
        this.role = role;
        this.departament = departament;
    }
    public Profile(Profile perfil) {
        this.idprofile = perfil.idprofile;
        this.idapplicative = perfil.idapplicative;
        this.idrole = perfil.idrole;
        this.iddepartament = perfil.iddepartament;
        this.active = perfil.active;
        this.applicative = perfil.applicative;
        this.role = perfil.role;
        this.departament = perfil.departament;
    }

 

    public Integer getIdprofile() {
        return idprofile;
    }

    public void setIdprofile(Integer idprofile) {
        this.idprofile = idprofile;
    }

    public Integer getIdapplicative() {
        return idapplicative;
    }

    public void setIdapplicative(Integer idapplicative) {
        this.idapplicative = idapplicative;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }
  @Override
    public boolean equals(Object object) {
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.idprofile== null && other.idprofile!= null) || (this.idprofile!= null && !this.idprofile.equals(other.idprofile))) {
            return false;
        }
   return true;
    }
  
    public boolean equalsReflection(Object object) {
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.idprofile== null && other.idprofile!= null) || (this.idprofile!= null && !this.idprofile.equals(other.idprofile))) {
            return false;
        }
   return EqualsBuilder.reflectionEquals(this, object);
    }

}
