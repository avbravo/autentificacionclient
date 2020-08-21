/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.asistenciaclient.entity;

import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Ignore;
import com.avbravo.jmoordb.anotations.Referenced;
import java.util.Date;
import java.util.List;

/**
 *
 * @author avbravo
 * tipoboleta= ASISTENCIA, SALUD, TRABAJO-EXTRAORDINARIO
 * estadounidad=aprobado, rechazado, regresado
 * estadoautoridad=aprobado, rechazado, regresado
 */
public class Boletas {

   private Integer idboleta;
    private Date fecha;
      @Referenced(collection = "User",
            field = "username", javatype = "String", lazy = false,
            repository = "com.avbravo.autentificacion.repository.UserRepository")
    User user;   
         private String archivo;
    private String tipoboleta;   
    private Date fechainicial;
    private Date fechafinal;
    private String observacion;
    private String tipojustificacon;
    private String estadounidad;
    private String estadoautoridad;   
    @Embedded
    private List<Autorizacionunidad> autorizacionunidads;
    @Embedded
    private List<Autorizacionautoridad> autorizacionautoridad;
    private String numeroincapadicadsalud;
    private String tipojustificacionsalud;
    
    private String tareasextraordinario;
    private Date fechasolicitudextraordinario;
    private Date fecharegistoextraordinario;
    private Date fechacapturarextraordinario;
    private String active;

    public Boletas() {
    }

    public Boletas(Integer idboleta) {
        this.idboleta = idboleta;
    }

    public Boletas(Integer idboleta, Date fecha, User user, String archivo, String tipoboleta, Date fechainicial, Date fechafinal, String observacion, String tipojustificacon, String estadounidad, String estadoautoridad, List<Autorizacionunidad> autorizacionunidads, List<Autorizacionautoridad> autorizacionautoridad, String numeroincapadicadsalud, String tipojustificacionsalud, String tareasextraordinario, Date fechasolicitudextraordinario, Date fecharegistoextraordinario, Date fechacapturarextraordinario, String active) {
        this.idboleta = idboleta;
        this.fecha = fecha;
        this.user = user;
        this.archivo = archivo;
        this.tipoboleta = tipoboleta;
        this.fechainicial = fechainicial;
        this.fechafinal = fechafinal;
        this.observacion = observacion;
        this.tipojustificacon = tipojustificacon;
        this.estadounidad = estadounidad;
        this.estadoautoridad = estadoautoridad;
        this.autorizacionunidads = autorizacionunidads;
        this.autorizacionautoridad = autorizacionautoridad;
        this.numeroincapadicadsalud = numeroincapadicadsalud;
        this.tipojustificacionsalud = tipojustificacionsalud;
        this.tareasextraordinario = tareasextraordinario;
        this.fechasolicitudextraordinario = fechasolicitudextraordinario;
        this.fecharegistoextraordinario = fecharegistoextraordinario;
        this.fechacapturarextraordinario = fechacapturarextraordinario;
        this.active = active;
    }

    
    
    
    
    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

   
    

    
    
    public Integer getIdboleta() {
        return idboleta;
    }

    public void setIdboleta(Integer idboleta) {
        this.idboleta = idboleta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTipoboleta() {
        return tipoboleta;
    }

    public void setTipoboleta(String tipoboleta) {
        this.tipoboleta = tipoboleta;
    }

    public Date getFechainicial() {
        return fechainicial;
    }

    public void setFechainicial(Date fechainicial) {
        this.fechainicial = fechainicial;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTipojustificacon() {
        return tipojustificacon;
    }

    public void setTipojustificacon(String tipojustificacon) {
        this.tipojustificacon = tipojustificacon;
    }

    public String getEstadounidad() {
        return estadounidad;
    }

    public void setEstadounidad(String estadounidad) {
        this.estadounidad = estadounidad;
    }

    public String getEstadoautoridad() {
        return estadoautoridad;
    }

    public void setEstadoautoridad(String estadoautoridad) {
        this.estadoautoridad = estadoautoridad;
    }

    public List<Autorizacionunidad> getAutorizacionunidads() {
        return autorizacionunidads;
    }

    public void setAutorizacionunidads(List<Autorizacionunidad> autorizacionunidads) {
        this.autorizacionunidads = autorizacionunidads;
    }

    public List<Autorizacionautoridad> getAutorizacionautoridad() {
        return autorizacionautoridad;
    }

    public void setAutorizacionautoridad(List<Autorizacionautoridad> autorizacionautoridad) {
        this.autorizacionautoridad = autorizacionautoridad;
    }

    public String getNumeroincapadicadsalud() {
        return numeroincapadicadsalud;
    }

    public void setNumeroincapadicadsalud(String numeroincapadicadsalud) {
        this.numeroincapadicadsalud = numeroincapadicadsalud;
    }

    public String getTipojustificacionsalud() {
        return tipojustificacionsalud;
    }

    public void setTipojustificacionsalud(String tipojustificacionsalud) {
        this.tipojustificacionsalud = tipojustificacionsalud;
    }

    public String getTareasextraordinario() {
        return tareasextraordinario;
    }

    public void setTareasextraordinario(String tareasextraordinario) {
        this.tareasextraordinario = tareasextraordinario;
    }

    public Date getFechasolicitudextraordinario() {
        return fechasolicitudextraordinario;
    }

    public void setFechasolicitudextraordinario(Date fechasolicitudextraordinario) {
        this.fechasolicitudextraordinario = fechasolicitudextraordinario;
    }

    public Date getFecharegistoextraordinario() {
        return fecharegistoextraordinario;
    }

    public void setFecharegistoextraordinario(Date fecharegistoextraordinario) {
        this.fecharegistoextraordinario = fecharegistoextraordinario;
    }

    public Date getFechacapturarextraordinario() {
        return fechacapturarextraordinario;
    }

    public void setFechacapturarextraordinario(Date fechacapturarextraordinario) {
        this.fechacapturarextraordinario = fechacapturarextraordinario;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

       
    
    
 
    
    
}
