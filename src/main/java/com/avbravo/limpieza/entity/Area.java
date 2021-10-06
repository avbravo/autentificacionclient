/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.limpieza.entity;

import com.avbravo.autentificacionclient.entity.Building;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
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

    @Referenced(collection = "TipoArea",
            field = "idtipoarea", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.limpieza.repository.TipoAreaRepository")
    private TipoArea tipoArea;
     @Referenced(collection = "Building",
            field = "idbuilding", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.BuildingRepository")
    private Building building;

    public Area() {
    }

    public Area(Integer idarea, String area, String icon, String descripcion, Boolean active, TipoArea tipoArea, Building building) {
        this.idarea = idarea;
        this.area = area;
        this.icon = icon;
        this.descripcion = descripcion;
        this.active = active;
        this.tipoArea = tipoArea;
        this.building = building;
    }

    
    
    
    public TipoArea getTipoArea() {
        return tipoArea;
    }

    public void setTipoArea(TipoArea tipoArea) {
        this.tipoArea = tipoArea;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
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
