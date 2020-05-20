/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import com.avbravo.jmoordb.anotations.Secondary;
import com.avbravo.jmoordb.pojos.UserInfo;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author avbravo
 *
 */
public class Usuario {
@Id
    private Integer iduser;
    private String username;
    private String password;
    private String nombre;
    private String email;

    @Embedded
    List<Perfil> perfil;
    @Embedded
    List<Ubicacion> ubicacion;

    private String activo;
 
    public Usuario() {
    }

    public Usuario(Integer iduser, String username, String password, String nombre, String email, List<Perfil> perfil, List<Ubicacion> ubicacion, String activo) {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.email = email;
        this.perfil = perfil;
        this.ubicacion = ubicacion;
        this.activo = activo;
    }

    
    
    
    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

    public List<Perfil> getPerfil() {
        return perfil;
    }

    public void setPerfil(List<Perfil> perfil) {
        this.perfil = perfil;
    }

  

    public List<Ubicacion> getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(List<Ubicacion> ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

   
}
