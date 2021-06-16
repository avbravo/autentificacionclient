/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.siuclient.entity;

import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;

/**
 *
 * @author avbravo
 */
public class Estudiante {

    @Id
    private String idestudiante;
    private String nombre;
    private String apellido;
    private String celular;
    private String telefono;
    private String email;
    @Referenced(collection = "Colegio",
            field = "idcolegio", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.siu.repository.ColegioRepository")
    private Colegio colegio;
   private Boolean active;

    public Estudiante() {
    }

    public Estudiante(String idestudiante, String nombre, String apellido, String celular, String telefono, String email, Colegio colegio, Boolean active) {
        this.idestudiante = idestudiante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.telefono = telefono;
        this.email = email;
        this.colegio = colegio;
        this.active = active;
    }

    
    
    public String getIdestudiante() {
        return idestudiante;
    }

    public void setIdestudiante(String idestudiante) {
        this.idestudiante = idestudiante;
    }

    


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Colegio getColegio() {
        return colegio;
    }

    public void setColegio(Colegio colegio) {
        this.colegio = colegio;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    

    
    
}
