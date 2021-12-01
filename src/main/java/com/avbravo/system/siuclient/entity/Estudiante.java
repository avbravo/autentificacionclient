/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.system.siuclient.entity;

import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import javax.validation.constraints.Email;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author avbravo
 */
public class Estudiante {

    @Id
    private Integer idestudiante;
    private String cedula;
    private String nombre;
    private String apellido;
    private String celular;
    private String telefono;
    @Email(message = "Email no es valido", regexp=".+@.+\\..+")
    private String email;
    @Referenced(collection = "Colegio",
            field = "idcolegio", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.siu.repository.ColegioRepository")
    private Colegio colegio;
   private Boolean active;

    public Estudiante() {
    }

    public Estudiante(Integer idestudiante, String cedula, String nombre, String apellido, String celular, String telefono, String email, Colegio colegio, Boolean active) {
        this.idestudiante = idestudiante;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.telefono = telefono;
        this.email = email;
        this.colegio = colegio;
        this.active = active;
    }

    
    
   
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Integer getIdestudiante() {
        return idestudiante;
    }

    public void setIdestudiante(Integer idestudiante) {
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

     @Override
    public boolean equals(Object object) {
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.idestudiante == null && other.idestudiante!= null) || (this.idestudiante!= null && !this.idestudiante.equals(other.idestudiante))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.idestudiante == null && other.idestudiante != null) || (this.idestudiante!= null && !this.idestudiante.equals(other.idestudiante))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }

    
    
}
