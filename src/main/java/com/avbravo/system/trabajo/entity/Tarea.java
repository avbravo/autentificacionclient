/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.system.trabajo.entity;

import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
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
public class Tarea {
 @Id
 private Integer idtarea;
 private String tarea;
 private Date date;
 private Boolean ejecutado;
 private Boolean active;
 @Embedded 
private User user;

    public Tarea() {
    }

    public Tarea(Integer idtarea, String tarea, Date date, Boolean ejecutado, Boolean active, User user) {
        this.idtarea = idtarea;
        this.tarea = tarea;
        this.date = date;
        this.ejecutado = ejecutado;
        this.active = active;
        this.user = user;
    }
 
 
 
 
 
  @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
        Tarea other = (Tarea) object;
        if ((this.idtarea == null && other.idtarea!= null) || (this.idtarea!= null && !this.idtarea.equals(other.idtarea))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
 Tarea other = (Tarea) object;
        if ((this.idtarea == null && other.idtarea != null) || (this.idtarea!= null && !this.idtarea.equals(other.idtarea))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
}
