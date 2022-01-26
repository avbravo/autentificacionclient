/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.system.trabajo.entity;

import com.avbravo.system.limpieza.entity.*;
import com.avbravo.autentificacionclient.entity.Building;
import com.avbravo.autentificacionclient.entity.Departament;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import static org.primefaces.component.linkbutton.LinkButtonBase.PropertyKeys.icon;

/**
 *
 * @author avbravo
 */
public class Sprint {
    @Id
    private Integer idsprint;
    private String descripcion;   
    private Date desde;
    private Date hasta;
    private Boolean active;
    private Boolean finalizado;

   @Referenced(collection = "Departament",
            field = "iddepartament", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.DepartamentRepository")
  Departament departament;
    public Sprint() {
    }

    public Integer getIdsprint() {
        return idsprint;
    }

    public void setIdsprint(Integer idsprint) {
        this.idsprint = idsprint;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
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
        if (!(object instanceof Sprint)) {
            return false;
        }
        Sprint other = (Sprint) object;
        if ((this.idsprint == null && other.idsprint!= null) || (this.idsprint!= null && !this.idsprint.equals(other.idsprint))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
        Sprint other = (Sprint) object;
        if ((this.idsprint == null && other.idsprint != null) || (this.idsprint!= null && !this.idsprint.equals(other.idsprint))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
    
    
}
