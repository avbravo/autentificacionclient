/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.limpieza.entity;

import com.avbravo.jmoordb.anotations.Id;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author avbravo
 */
public class TipoArea {
    @Id
    private Integer idtipoarea;
    private String tipoarea;
   
    private Boolean active;

    public TipoArea() {
    }

    public TipoArea(Integer idtipoarea, String tipoarea, Boolean active) {
        this.idtipoarea = idtipoarea;
        this.tipoarea = tipoarea;
        this.active = active;
    }

    
    
    
    public Integer getIdtipoarea() {
        return idtipoarea;
    }

    public void setIdtipoarea(Integer idtipoarea) {
        this.idtipoarea = idtipoarea;
    }

    public String getTipoarea() {
        return tipoarea;
    }

    public void setTipoarea(String tipoarea) {
        this.tipoarea = tipoarea;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

  

   
     @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoArea)) {
            return false;
        }
        TipoArea other = (TipoArea) object;
        if ((this.idtipoarea == null && other.idtipoarea!= null) || (this.idtipoarea!= null && !this.idtipoarea.equals(other.idtipoarea))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof TipoArea)) {
            return false;
        }
        TipoArea other = (TipoArea) object;
        if ((this.idtipoarea == null && other.idtipoarea != null) || (this.idtipoarea!= null && !this.idtipoarea.equals(other.idtipoarea))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
    
}
