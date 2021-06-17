/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.siuclient.entity;

import com.avbravo.autentificacionclient.entity.Province;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author avbravo
 */
public class Colegio {
    @Id
    private Integer idcolegio;
    private String colegio;
   @Referenced(collection = "Province",
            field = "idprovince", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.ProvinceRepository")
    Province province;
   
    private Boolean active;

    public Colegio() {
    }

    public Colegio(Integer idcolegio, String colegio, Province province, Boolean active) {
        this.idcolegio = idcolegio;
        this.colegio = colegio;
        this.province = province;
        this.active = active;
    }

    public Integer getIdcolegio() {
        return idcolegio;
    }

    public void setIdcolegio(Integer idcolegio) {
        this.idcolegio = idcolegio;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

   
    
    
    
     @Override
    public boolean equals(Object object) {
        if (!(object instanceof Colegio)) {
            return false;
        }
        Colegio other = (Colegio) object;
        if ((this.idcolegio == null && other.idcolegio!= null) || (this.idcolegio!= null && !this.idcolegio.equals(other.idcolegio))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Colegio)) {
            return false;
        }
        Colegio other = (Colegio) object;
        if ((this.idcolegio == null && other.idcolegio != null) || (this.idcolegio!= null && !this.idcolegio.equals(other.idcolegio))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
   
    
    
    
}
