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
public class Autorizacionautoridad {
    @Id
    private Integer idautorizacionautoridad;
    private String username;
    private Date fecha;
    private String comentario;
    private String estado;
    @Ignore
    private User user;

    public Autorizacionautoridad() {
    }

    public Autorizacionautoridad(Integer idautorizacionautoridad, String username, Date fecha, String comentario, String estado, User user) {
        this.idautorizacionautoridad = idautorizacionautoridad;
        this.username = username;
        this.fecha = fecha;
        this.comentario = comentario;
        this.estado = estado;
        this.user = user;
    }

    public Integer getIdautorizacionautoridad() {
        return idautorizacionautoridad;
    }

    public void setIdautorizacionautoridad(Integer idautorizacionautoridad) {
        this.idautorizacionautoridad = idautorizacionautoridad;
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
