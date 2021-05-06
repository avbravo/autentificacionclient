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
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 *
 * @author avbravo
 */
public class Autorizacionhistorial {
    @Id
    private Integer idautorizacionhistorial;
    private String username;
    private Date fecha;
    private String comentario;
    private String estado;

    @Ignore
    private User user;

    public Autorizacionhistorial() {
    }

    public Autorizacionhistorial(Integer idautorizacionhistorial, String username, Date fecha, String comentario, String estado, User user) {
        this.idautorizacionhistorial = idautorizacionhistorial;
        this.username = username;
        this.fecha = fecha;
        this.comentario = comentario;
        this.estado = estado;
        this.user = user;
    }

    public Integer getIdautorizacionhistorial() {
        return idautorizacionhistorial;
    }

    public void setIdautorizacionhistorial(Integer idautorizacionhistorial) {
        this.idautorizacionhistorial = idautorizacionhistorial;
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

   @Override
    public boolean equals(Object object) {
        if (!(object instanceof Autorizacionhistorial)) {
            return false;
        }
       Autorizacionhistorial other = (Autorizacionhistorial) object;
        if ((this.idautorizacionhistorial== null && other.idautorizacionhistorial != null) || (this.idautorizacionhistorial  != null && !this.idautorizacionhistorial.equals(other.idautorizacionhistorial ))) {
            return false;
        }
      return true;
    }
   
    public boolean equalsReflection(Object object) {
        if (!(object instanceof Autorizacionhistorial)) {
            return false;
        }
       Autorizacionhistorial other = (Autorizacionhistorial) object;
        if ((this.idautorizacionhistorial== null && other.idautorizacionhistorial != null) || (this.idautorizacionhistorial  != null && !this.idautorizacionhistorial.equals(other.idautorizacionhistorial ))) {
            return false;
        }
      return EqualsBuilder.reflectionEquals(this, object);
    }

  
    
    
}
