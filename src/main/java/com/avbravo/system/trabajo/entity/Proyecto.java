/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.system.trabajo.entity;

import com.avbravo.autentificacionclient.entity.Departament;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author avbravo
 */
@Data
@Builder
public class Proyecto {
    @Id
    private Integer idproyecto;
    private String proyecto;   
    private String descripcion;
    private Date desde;
    private Date hasta;
    private Boolean active;
    private Boolean finalizado;

   @Referenced(collection = "Departament",
            field = "iddepartament", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.DepartamentRepository")
 private Departament departament;
   
  
    public Proyecto() {
    }

    public Proyecto(Integer idproyecto, String proyecto, String descripcion, Date desde, Date hasta, Boolean active, Boolean finalizado, Departament departament) {
        this.idproyecto = idproyecto;
        this.proyecto = proyecto;
        this.descripcion = descripcion;
        this.desde = desde;
        this.hasta = hasta;
        this.active = active;
        this.finalizado = finalizado;
        this.departament = departament;
    }

  
    
     @Override
    public boolean equals(Object object) {
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.idproyecto == null && other.idproyecto!= null) || (this.idproyecto!= null && !this.idproyecto.equals(other.idproyecto))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.idproyecto == null && other.idproyecto != null) || (this.idproyecto!= null && !this.idproyecto.equals(other.idproyecto))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
    
    
}
