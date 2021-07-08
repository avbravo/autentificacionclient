/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author avbravo
 */
public class TopSearch {

    @Id
    private Integer idtopsearch;
    @Referenced(collection = "Applicative",
            field = "idapplicative", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.ApplicativeRepository")
    Applicative applicative;
    private String key;
    private String url;

    @Referenced(collection = "Role",
            field = "idrole", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.RoleRepository")
   Role role;

    private String descripcion;
    private Boolean active;

    public TopSearch() {
    }

    public TopSearch(Integer idtopsearch, Applicative applicative, String key, String url, Role role, String descripcion, Boolean active) {
        this.idtopsearch = idtopsearch;
        this.applicative = applicative;
        this.key = key;
        this.url = url;
        this.role = role;
        this.descripcion = descripcion;
        this.active = active;
    }

    
    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   

   
    
    public Integer getIdtopsearch() {
        return idtopsearch;
    }

    public void setIdtopsearch(Integer idtopsearch) {
        this.idtopsearch = idtopsearch;
    }

   

    public Applicative getApplicative() {
        return applicative;
    }

    public void setApplicative(Applicative applicative) {
        this.applicative = applicative;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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
        if (!(object instanceof TopSearch)) {
            return false;
        }
        TopSearch other = (TopSearch) object;
        if ((this.idtopsearch == null && other.idtopsearch != null) || (this.idtopsearch!= null && !this.idtopsearch.equals(other.idtopsearch))) {
            return false;
        }
        return true;
    }

    
    public boolean equalsReflection(Object object) {
        if (!(object instanceof TopSearch)) {
            return false;
        }
        TopSearch other = (TopSearch) object;
        if ((this.idtopsearch == null && other.idtopsearch != null) || (this.idtopsearch != null && !this.idtopsearch.equals(other.idtopsearch))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    

}
