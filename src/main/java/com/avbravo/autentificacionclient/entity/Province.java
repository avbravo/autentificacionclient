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
public class Province {
    @Id
    private Integer idprovince;
    private String province;
   @Referenced(collection = "Country",
            field = "idcountry", javatype = "String", lazy = false,
            repository = "com.avbravo.autentificacion.repository.CountryRepository")
    Country country;
    private Boolean active;

    public Province() {
    }

    
    
    
    public Province(Integer idprovince, String province, Country country, Boolean active) {
        this.idprovince = idprovince;
        this.province = province;
        this.country = country;
        this.active = active;
    }

    
    
    
    public Integer getIdprovince() {
        return idprovince;
    }

    public void setIdprovince(Integer idprovince) {
        this.idprovince = idprovince;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    
      @Override
    public boolean equals(Object object) {
        if (!(object instanceof Province)) {
            return false;
        }
        Province other = (Province) object;
        if ((this.idprovince == null && other.idprovince!= null) || (this.idprovince!= null && !this.idprovince.equals(other.idprovince))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Province)) {
            return false;
        }
        Province other = (Province) object;
        if ((this.idprovince == null && other.idprovince != null) || (this.idprovince!= null && !this.idprovince.equals(other.idprovince))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
    
    
}
