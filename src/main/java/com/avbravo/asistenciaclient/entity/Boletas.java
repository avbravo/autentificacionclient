/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.asistenciaclient.entity;

import com.avbravo.autentificacionclient.entity.Departament;
import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
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
        @Id
 private Integer idboleta;
    private Date fecha;
      @Referenced(collection = "User",
            field = "username", javatype = "String", lazy = false,
            repository = "com.avbravo.autentificacion.repository.UserRepository")
    User user;   
      @Referenced(collection = "Departament",
            field = "iddepartament", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.DepartamentRepository")
    Departament departament;
    private String archivo;
    private String archivo2;
    private String archivo3;
    private String tipoboleta;   
    private Date fechainicial;
    private Date fechafinal;
    private String observacion;
    private String tipojustificacon;
    private String estadounidad;
    private String estadoautoridad;   
    @Embedded
    private List<Autorizacionhistorial> autorizacionhistorial;
    @Embedded
      private String numeroincapadicadsalud;
    private String tipojustificacionsalud;
       
    private String active;

     
    
    public Boletas() {
    }

    public Boletas(Integer idboleta, Date fecha, User user, Departament departament, String archivo, String archivo2, String archivo3, String tipoboleta, Date fechainicial, Date fechafinal, String observacion, String tipojustificacon, String estadounidad, String estadoautoridad, List<Autorizacionhistorial> autorizacionhistorial, String numeroincapadicadsalud, String tipojustificacionsalud, String active) {
        this.idboleta = idboleta;
        this.fecha = fecha;
        this.user = user;
        this.departament = departament;
        this.archivo = archivo;
        this.archivo2 = archivo2;
        this.archivo3 = archivo3;
        this.tipoboleta = tipoboleta;
        this.fechainicial = fechainicial;
        this.fechafinal = fechafinal;
        this.observacion = observacion;
        this.tipojustificacon = tipojustificacon;
        this.estadounidad = estadounidad;
        this.estadoautoridad = estadoautoridad;
        this.autorizacionhistorial = autorizacionhistorial;
        this.numeroincapadicadsalud = numeroincapadicadsalud;
        this.tipojustificacionsalud = tipojustificacionsalud;
        this.active = active;
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

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivo2() {
        return archivo2;
    }

    public void setArchivo2(String archivo2) {
        this.archivo2 = archivo2;
    }

    public String getArchivo3() {
        return archivo3;
    }

    public void setArchivo3(String archivo3) {
        this.archivo3 = archivo3;
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

    public List<Autorizacionhistorial> getAutorizacionhistorial() {
        return autorizacionhistorial;
    }

    public void setAutorizacionhistorial(List<Autorizacionhistorial> autorizacionhistorial) {
        this.autorizacionhistorial = autorizacionhistorial;
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

   
   
 
    

}
