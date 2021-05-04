/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 *
 * @author avbravo
 */
public class Access {
    @Id
    private Integer idaccess;
     
@Referenced(collection = "User",
            field = "iduser", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.UsertRepository")
    private User user;

@Embedded
private List<Entrancees> entrancees;

    public Access() {
    }

    public Access(Integer idaccess, User user, List<Entrancees> entrancees) {
        this.idaccess = idaccess;
        this.user = user;
        this.entrancees = entrancees;
    }

    public Integer getIdaccess() {
        return idaccess;
    }

    public void setIdaccess(Integer idaccess) {
        this.idaccess = idaccess;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Entrancees> getEntrancees() {
        return entrancees;
    }

    public void setEntrancees(List<Entrancees> entrancees) {
        this.entrancees = entrancees;
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
      return EqualsBuilder.reflectionEquals(this, object);
    }



    
}
