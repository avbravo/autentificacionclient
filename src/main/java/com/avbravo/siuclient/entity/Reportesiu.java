/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.siuclient.entity;

import com.avbravo.jmoordb.anotations.Id;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author avbravo
 */
public class Reportesiu {
    @Id
    private Integer idreportesiu;
    private String dias;
    private Integer anio;
   
    private Boolean active;

    public Reportesiu() {
    }

    public Reportesiu(Integer idreportesiu, String dias, Integer anio, Boolean active) {
        this.idreportesiu = idreportesiu;
        this.dias = dias;
        this.anio = anio;
        this.active = active;
    }

    
    
    
    public Integer getIdreportesiu() {
        return idreportesiu;
    }

    public void setIdreportesiu(Integer idreportesiu) {
        this.idreportesiu = idreportesiu;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

   
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Reportesiu)) {
            return false;
        }
        Reportesiu other = (Reportesiu) object;
        if ((this.idreportesiu == null && other.idreportesiu!= null) || (this.idreportesiu!= null && !this.idreportesiu.equals(other.idreportesiu))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Reportesiu)) {
            return false;
        }
        Reportesiu other = (Reportesiu) object;
        if ((this.idreportesiu == null && other.idreportesiu != null) || (this.idreportesiu!= null && !this.idreportesiu.equals(other.idreportesiu))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
   
    
    
    
}
