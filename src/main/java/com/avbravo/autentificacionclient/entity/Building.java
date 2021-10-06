/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.autentificacionclient.entity.Departament;
import com.avbravo.autentificacionclient.entity.Headquarters;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import com.avbravo.jmoordb.anotations.Secondary;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author avbravo
 */
public class Building {
       @Id
    private Integer idbuilding;
    @Secondary
    private String building;
    private Boolean active;
    private String shortname;
   
@Referenced(collection = "Headquarters",
            field = "idheadquarters", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.HeadquartersRepository")
    private Headquarters headquarters;

    public Building() {
    }

    public Building(Integer idbuilding, String building, Boolean active, String shortname, Headquarters headquarters) {
        this.idbuilding = idbuilding;
        this.building = building;
        this.active = active;
        this.shortname = shortname;
        this.headquarters = headquarters;
    }

   

    public Integer getIdbuilding() {
        return idbuilding;
    }

    public void setIdbuilding(Integer idbuilding) {
        this.idbuilding = idbuilding;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
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
        if (!(object instanceof Building)) {
            return false;
        }
     Building other = (Building) object;
        if ((this.idbuilding == null && other.idbuilding != null) || (this.idbuilding != null && !this.idbuilding.equals(other.idbuilding))) {
            return false;
        }
      return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Departament)) {
            return false;
        }
         Building other = (Building) object;
        if ((this.idbuilding == null && other.idbuilding != null) || (this.idbuilding != null && !this.idbuilding.equals(other.idbuilding))) {
            return false;
        }
      return EqualsBuilder.reflectionEquals(this, object);
    }

}
