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
 *
 */
public class Headquarters {
@Id
    private Integer idheadquarters ;
    private String headquarters ;

    @Referenced(collection = "Institution",
            field = "idinstitution", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.InstitutionRepository")
    Institution institution;

    private Boolean active;

    public Headquarters() {
    }

    public Headquarters(Integer idheadquarters, String headquarters, Institution institution, Boolean active) {
        this.idheadquarters = idheadquarters;
        this.headquarters = headquarters;
        this.institution = institution;
        this.active = active;
    }


    public Integer getIdheadquarters() {
        return idheadquarters;
    }

    public void setIdheadquarters(Integer idheadquarters) {
        this.idheadquarters = idheadquarters;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

   

    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Headquarters)) {
            return false;
        }
       Headquarters other = (Headquarters) object;
        if ((this.idheadquarters == null && other.idheadquarters != null) || (this.idheadquarters != null && !this.idheadquarters.equals(other.idheadquarters))) {
            return false;
        }
     return true;
    }
    
    
    public boolean equalsReflection(Object object) {
        if (!(object instanceof Headquarters)) {
            return false;
        }
       Headquarters other = (Headquarters) object;
        if ((this.idheadquarters == null && other.idheadquarters != null) || (this.idheadquarters != null && !this.idheadquarters.equals(other.idheadquarters))) {
            return false;
        }
     return EqualsBuilder.reflectionEquals(this, object);
    }

        
}
