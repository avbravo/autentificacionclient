/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import com.avbravo.jmoordb.anotations.Secondary;
import com.avbravo.jmoordb.pojos.UserInfo;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class Departament {

    @Id
    private Integer iddepartament;
    @Secondary
    private String departament;
    private String active;
   
@Referenced(collection = "Headquarters",
            field = "idheadquarters", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.HeadquartersRepository")
    private Headquarters headquarters;
    
    public Departament() {
    }

    public Departament(Integer iddepartament, String departament, String active, Headquarters headquarters) {
        this.iddepartament = iddepartament;
        this.departament = departament;
        this.active = active;
        this.headquarters = headquarters;
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Headquarters getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(Headquarters headquarters) {
        this.headquarters = headquarters;
    }

   

}
