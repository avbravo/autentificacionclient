/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.limpieza.entity;

import com.avbravo.jmoordb.anotations.Id;

/**
 *
 * @author avbravo
 */
public class Area {
    @Id
    private Integer idarea;
    private String area;
    private String descripcion;   
    private Boolean active;

    public Area() {
    }

    public Area(Integer idarea, String area, String descripcion, Boolean active) {
        this.idarea = idarea;
        this.area = area;
        this.descripcion = descripcion;
        this.active = active;
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
    
    
    
    
}
