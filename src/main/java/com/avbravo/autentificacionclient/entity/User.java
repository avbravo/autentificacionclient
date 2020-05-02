/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Referenced;
import com.avbravo.jmoordb.pojos.UserInfo;
import java.util.List;
import java.util.Objects;


/**
 *
 * @author avbravo
 *
 */
public class User {

    private Integer iduser;
    private String username;
    private String password;
    private String nombre;
    private String email;

    @Referenced(collection = "Sede",
            field = "idsede", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.SedeRepository")
    Sede sede;

    @Embedded
    List<Profile> profile;

    private String activo;
    @Embedded
    List<UserInfo> userInfo;

    public User() {
    }

    public User(Integer iduser, String username, String password, String nombre, String email, Sede sede, List<Profile> profile, String activo, List<UserInfo> userInfo) {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.email = email;
        this.sede = sede;
        this.profile = profile;
        this.activo = activo;
        this.userInfo = userInfo;
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

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public List<Profile> getProfile() {
        return profile;
    }

    public void setProfile(List<Profile> profile) {
        this.profile = profile;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public List<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }

      @Override
    public int hashCode() {
        return Objects.hashCode(iduser);
    }
}
