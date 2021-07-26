/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.limpieza.entity;

import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Ignore;
import com.avbravo.jmoordb.anotations.Referenced;
import java.util.Date;

/**
 *
 * @author avbravo
 */
public class Limpieza {

    @Id
    private Integer idlimpieza;
    private Date fechahora;
    @Referenced(collection = "Area",
            field = "idarea", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.limpieza.repository.AreaRepository")
    private Area area;
    @Referenced(collection = "User",
            field = "idarea", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.UserRepository")
    private User user;
    private String observacion;
    private Date fechahorasupervision;
    private String usernamesupervisor;
    @Ignore
    private User supervisor;
    private String observacionsupervisor;
    private Boolean active;

    public Limpieza() {
    }

    public Limpieza(Integer idlimpieza, Date fechahora, Area area, User user, String observacion, Date fechahorasupervision, String usernamesupervisor, User supervisor, String observacionsupervisor, Boolean active) {
        this.idlimpieza = idlimpieza;
        this.fechahora = fechahora;
        this.area = area;
        this.user = user;
        this.observacion = observacion;
        this.fechahorasupervision = fechahorasupervision;
        this.usernamesupervisor = usernamesupervisor;
        this.supervisor = supervisor;
        this.observacionsupervisor = observacionsupervisor;
        this.active = active;
    }

    public Integer getIdlimpieza() {
        return idlimpieza;
    }

    public void setIdlimpieza(Integer idlimpieza) {
        this.idlimpieza = idlimpieza;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechahorasupervision() {
        return fechahorasupervision;
    }

    public void setFechahorasupervision(Date fechahorasupervision) {
        this.fechahorasupervision = fechahorasupervision;
    }

    public String getUsernamesupervisor() {
        return usernamesupervisor;
    }

    public void setUsernamesupervisor(String usernamesupervisor) {
        this.usernamesupervisor = usernamesupervisor;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }

    public String getObservacionsupervisor() {
        return observacionsupervisor;
    }

    public void setObservacionsupervisor(String observacionsupervisor) {
        this.observacionsupervisor = observacionsupervisor;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    
    
    
}
