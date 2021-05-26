/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 *
 * @author avbravo
 */
public class Access {
     @Id
    private Integer idaccess;
    private String username;
    private Date date;
    private Integer idprofile;
    private Integer idapplicative;
    private Integer idrole;
    private Integer iddepartament;

    public Access() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Integer getIdaccess() {
        return idaccess;
    }

    public void setIdaccess(Integer idaccess) {
        this.idaccess = idaccess;
    }

   

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Applicative)) {
            return false;
        }
        Access other = (Access) object;
        if ((this.idaccess == null && other.idaccess != null) || (this.idaccess != null && !this.idaccess.equals(other.idaccess))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Applicative)) {
            return false;
        }
        Access other = (Access) object;
        if ((this.idaccess == null && other.idaccess != null) || (this.idaccess != null && !this.idaccess.equals(other.idaccess))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
}
