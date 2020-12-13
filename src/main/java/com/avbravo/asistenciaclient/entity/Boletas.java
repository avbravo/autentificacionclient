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
import com.avbravo.jmoordb.anotations.Ignore;
import com.avbravo.jmoordb.anotations.Referenced;
import java.util.Date;
import java.util.List;

/**
 *
 * @author avbravo tipoboleta= ASISTENCIA, SALUD, TRABAJO-EXTRAORDINARIO
 * estadounidad=aprobado, rechazado, regresado estadoautoridad=aprobado,
 * rechazado, regresado
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
    private String gradoconsanguinidad;
    @Embedded
    private List<Autorizacionhistorial> autorizacionhistorial;
    @Embedded
    private String numeroincapadicadsalud;
    private String tipojustificacionsalud;

    private Boolean active;
    
    private Boolean captadoensistemaaistencia;
    @Ignore
    private Boolean  essekeccionada;
    
    @Ignore
    private String comentario;
    
    public Boletas() {
    }

    public Boolean getEssekeccionada() {
        return essekeccionada;
    }

    public void setEssekeccionada(Boolean essekeccionada) {
        this.essekeccionada = essekeccionada;
    }

  
    
    
    public Boolean getCaptadoensistemaaistencia() {
        return captadoensistemaaistencia;
    }

    public void setCaptadoensistemaaistencia(Boolean captadoensistemaaistencia) {
        this.captadoensistemaaistencia = captadoensistemaaistencia;
    }

    public String getGradoconsanguinidad() {
        return gradoconsanguinidad;
    }

    public void setGradoconsanguinidad(String gradoconsanguinidad) {
        this.gradoconsanguinidad = gradoconsanguinidad;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

  

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Boletas)) {
            return false;
        }
        Boletas other = (Boletas) object;
        if ((this.idboleta == null && other.idboleta != null) || (this.idboleta != null && !this.idboleta.equals(other.idboleta))) {
            return false;
        }
        return true;
    }

}
