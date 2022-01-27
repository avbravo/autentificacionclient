/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.system.trabajo.entity;

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
public class Sprint {

    @Id
    private Integer idsprint;
    private String descripcion;
    private Date desde;
    private Date hasta;
    private Boolean active;
    private Boolean finalizado;
    @Referenced(collection = "Proyecto",
            field = "idproyecto", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.trabajo.repository.ProyectoRepository")
    private Proyecto proyecto;
    @Referenced(collection = "Tarjeta",
            field = "idtarjeta", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.trabajo.repository.TarjetaRepository")
    private List<Tarjeta> tarjeta;

    public Sprint() {
    }

    public Sprint(Integer idsprint, String descripcion, Date desde, Date hasta, Boolean active, Boolean finalizado, Proyecto proyecto, List<Tarjeta> tarjeta) {
        this.idsprint = idsprint;
        this.descripcion = descripcion;
        this.desde = desde;
        this.hasta = hasta;
        this.active = active;
        this.finalizado = finalizado;
        this.proyecto = proyecto;
        this.tarjeta = tarjeta;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
        Sprint other = (Sprint) object;
        if ((this.idsprint == null && other.idsprint != null) || (this.idsprint != null && !this.idsprint.equals(other.idsprint))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
        Sprint other = (Sprint) object;
        if ((this.idsprint == null && other.idsprint != null) || (this.idsprint != null && !this.idsprint.equals(other.idsprint))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }

}
