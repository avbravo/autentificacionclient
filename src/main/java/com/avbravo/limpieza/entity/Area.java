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
public class Area {
    @Id
    private Integer idarea;
    private String area;
    private String descripcion;   
    private String icon;
    private Boolean active;

    public Area() {
    }

    public Area(Integer idarea, String area, String descripcion, String icon, Boolean active) {
        this.idarea = idarea;
        this.area = area;
        this.descripcion = descripcion;
        this.icon = icon;
        this.active = active;
    }

    
    
    
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

   
    
    
    public Integer getIdarea() {
        return idarea;
    }

    public void setIdarea(Integer idarea) {
        this.idarea = idarea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
     @Override
    public boolean equals(Object object) {
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.idarea == null && other.idarea!= null) || (this.idarea!= null && !this.idarea.equals(other.idarea))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.idarea == null && other.idarea != null) || (this.idarea!= null && !this.idarea.equals(other.idarea))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
    
    
}
