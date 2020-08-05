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
public class User {

    @Id
    private Integer iduser;
    private String username;
    private String password;
    private String name;
    private String email;
    private String cellphone;

    @Embedded
    private List<Profile> profile;

    private String active;

    public User() {
    }

    public User(Integer iduser, String username, String password, String name, String email, String cellphone, List<Profile> profile, String active) {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.profile = profile;
        this.active = active;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public List<Profile> getProfile() {
        return profile;
    }

    public void setProfile(List<Profile> profile) {
        this.profile = profile;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    
}
