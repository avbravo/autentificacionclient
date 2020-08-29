/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import java.util.List;

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
    private String sex;
    private String photo;

    @Embedded
    private List<Profile> profile;

    private String active;

    public User() {
    }

    public User(Integer iduser, String username, String password, String name, String email, String cellphone, String sex, String photo, List<Profile> profile, String active) {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.sex = sex;
        this.photo = photo;
        this.profile = profile;
        this.active = active;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
