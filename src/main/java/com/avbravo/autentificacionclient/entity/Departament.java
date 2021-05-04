/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import com.avbravo.jmoordb.anotations.Secondary;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author avbravo
 */
public class Departament {

    @Id
    private Integer iddepartament;
    @Secondary
    private String departament;
    private Boolean active;
    private String shortname;
   
@Referenced(collection = "Headquarters",
            field = "idheadquarters", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.HeadquartersRepository")
    private Headquarters headquarters;

private Boolean isapprovalchietunitrequired;
private Boolean isapprovalauthorityrequired;
    
    public Departament() {
    }

    public Departament(Integer iddepartament, String departament, Boolean active, String shortname, Headquarters headquarters, Boolean isapprovalchietunitrequired, Boolean isapprovalauthorityrequired) {
        this.iddepartament = iddepartament;
        this.departament = departament;
        this.active = active;
        this.shortname = shortname;
        this.headquarters = headquarters;
        this.isapprovalchietunitrequired = isapprovalchietunitrequired;
        this.isapprovalauthorityrequired = isapprovalauthorityrequired;
    }

    public Boolean getIsapprovalchietunitrequired() {
        return isapprovalchietunitrequired;
    }

    public void setIsapprovalchietunitrequired(Boolean isapprovalchietunitrequired) {
        this.isapprovalchietunitrequired = isapprovalchietunitrequired;
    }

    public Boolean getIsapprovalauthorityrequired() {
        return isapprovalauthorityrequired;
    }

    public void setIsapprovalauthorityrequired(Boolean isapprovalauthorityrequired) {
        this.isapprovalauthorityrequired = isapprovalauthorityrequired;
    }

   

    public Integer getIddepartament() {
        return iddepartament;
    }

    public void setIddepartament(Integer iddepartament) {
        this.iddepartament = iddepartament;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public Headquarters getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(Headquarters headquarters) {
        this.headquarters = headquarters;
    }

    
  
  
 @Override
    public boolean equals(Object object) {
        if (!(object instanceof Departament)) {
            return false;
        }
        Departament other = (Departament) object;
        if ((this.iddepartament == null && other.iddepartament != null) || (this.iddepartament != null && !this.iddepartament.equals(other.iddepartament))) {
            return false;
        }
       return true;
    }
 
    public boolean equalsReflection(Object object) {
        if (!(object instanceof Departament)) {
            return false;
        }
        Departament other = (Departament) object;
        if ((this.iddepartament == null && other.iddepartament != null) || (this.iddepartament != null && !this.iddepartament.equals(other.iddepartament))) {
            return false;
        }
       return EqualsBuilder.reflectionEquals(this, object);
    }

}
