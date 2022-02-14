/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.system.planeacion.entity;

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
public class Comentario {

    @Id
    private Integer idcomentario;
    private String comentario;
    private Date fecha;
    private Boolean active;
    @Embedded
   private User user;

    public Comentario() {
    }

    public Comentario(Integer idcomentario, String comentario, Date fecha, Boolean active, User user) {
        this.idcomentario = idcomentario;
        this.comentario = comentario;
        this.fecha = fecha;
        this.active = active;
        this.user = user;
    }

   

   

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.idcomentario == null && other.idcomentario != null) || (this.idcomentario != null && !this.idcomentario.equals(other.idcomentario))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.idcomentario == null && other.idcomentario != null) || (this.idcomentario != null && !this.idcomentario.equals(other.idcomentario))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }

}
