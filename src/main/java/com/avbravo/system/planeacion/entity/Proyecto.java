/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.system.planeacion.entity;

import com.avbravo.autentificacionclient.entity.Departament;
import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import java.util.Date;
import java.util.List;
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
    private String estado;
    private Double avance;

    @Referenced(collection = "Departament",
            field = "iddepartament", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.DepartamentRepository")
    private Departament departament;
    @Referenced(collection = "User",
            field = "iduser", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.UserRepository")
    private List<User> user;

    @Embedded
    List<Requisito> requisito;

    private Boolean active;

    public Proyecto() {
    }

    public Proyecto(Integer idproyecto, String proyecto, String descripcion, Date desde, Date hasta, String estado, Double avance, Departament departament, List<User> user, List<Requisito> requisito, Boolean active) {
        this.idproyecto = idproyecto;
        this.proyecto = proyecto;
        this.descripcion = descripcion;
        this.desde = desde;
        this.hasta = hasta;
        this.estado = estado;
        this.avance = avance;
        this.departament = departament;
        this.user = user;
        this.requisito = requisito;
        this.active = active;
    }

   
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.idproyecto == null && other.idproyecto != null) || (this.idproyecto != null && !this.idproyecto.equals(other.idproyecto))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.idproyecto == null && other.idproyecto != null) || (this.idproyecto != null && !this.idproyecto.equals(other.idproyecto))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }

}
