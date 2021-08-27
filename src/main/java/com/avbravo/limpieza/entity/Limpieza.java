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
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;

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
            field = "iduser", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.UserRepository")
    private List<User> user;
    private String estado;
    private String observacion;
    private Date fechahorasupervision;
    private String observacionsupervisor;
      private String estadosupervisor;
    private Boolean supervisado;
     private String archivo;
    private String archivoSupervisor;
    private Boolean active;

    public Limpieza() {
    }

    public Limpieza(Integer idlimpieza, Date fechahora, Area area, List<User> user, String estado, String observacion, Date fechahorasupervision, String observacionsupervisor, String estadosupervisor, Boolean supervisado, String archivo, String archivoSupervisor, Boolean active) {
        this.idlimpieza = idlimpieza;
        this.fechahora = fechahora;
        this.area = area;
        this.user = user;
        this.estado = estado;
        this.observacion = observacion;
        this.fechahorasupervision = fechahorasupervision;
        this.observacionsupervisor = observacionsupervisor;
        this.estadosupervisor = estadosupervisor;
        this.supervisado = supervisado;
        this.archivo = archivo;
        this.archivoSupervisor = archivoSupervisor;
        this.active = active;
    }

  
    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivoSupervisor() {
        return archivoSupervisor;
    }

    public void setArchivoSupervisor(String archivoSupervisor) {
        this.archivoSupervisor = archivoSupervisor;
    }

   

   

    public String getEstadosupervisor() {
        return estadosupervisor;
    }

    public void setEstadosupervisor(String estadosupervisor) {
        this.estadosupervisor = estadosupervisor;
    }

   

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

  
    
    
    public Boolean getSupervisado() {
        return supervisado;
    }

    public void setSupervisado(Boolean supervisado) {
        this.supervisado = supervisado;
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

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
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

   
   
      @Override
    public boolean equals(Object object) {
        if (!(object instanceof Limpieza)) {
            return false;
        }
        Limpieza other = (Limpieza) object;
        if ((this.idlimpieza == null && other.idlimpieza!= null) || (this.idlimpieza!= null && !this.idlimpieza.equals(other.idlimpieza))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Limpieza)) {
            return false;
        }
       Limpieza other = (Limpieza) object;
        if ((this.idlimpieza == null && other.idlimpieza!= null) || (this.idlimpieza!= null && !this.idlimpieza.equals(other.idlimpieza))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
    
    
}
