/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.asistenciaclient.entity;

import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Ignore;
import java.util.Date;

/**
 *
 * @author avbravo
 */
public class AutorizacionUnidad {
    @Id
    private Integer idaautorizacionunidad;
    private String username;
    private Date fecha;
    private String comentario;
    private String estado;
    @Ignore
    private User user;

    public AutorizacionUnidad() {
    }

    public AutorizacionUnidad(Integer idaautorizacionunidad, String username, Date fecha, String comentario, String estado, User user) {
        this.idaautorizacionunidad = idaautorizacionunidad;
        this.username = username;
        this.fecha = fecha;
        this.comentario = comentario;
        this.estado = estado;
        this.user = user;
    }

 

    public Integer getIdaautorizacionunidad() {
        return idaautorizacionunidad;
    }

    public void setIdaautorizacionunidad(Integer idaautorizacionunidad) {
        this.idaautorizacionunidad = idaautorizacionunidad;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
