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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Email;
import org.apache.commons.lang.builder.EqualsBuilder;

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
    @Email(message = "Email no es valido", regexp = ".+@.+\\..+")
    private String email;
    private String cellphone;
    private String sex;
    private String photo;
    private String identificationcard;
    private String socialsecuritynumber;

    @Embedded
    private List<Profile> profile;

    private Boolean active;

    public User() {
    }

    public User(User user) {
        this.iduser = user.iduser;
        this.username = user.username;
        this.password = user.password;
        this.name = user.name;
        this.email = user.email;
        this.cellphone = user.cellphone;
        this.sex = user.sex;
        this.photo = user.photo;
        this.identificationcard = user.identificationcard;
        this.socialsecuritynumber = user.socialsecuritynumber;
//        this.profile = user.profile;
        this.profile = new ArrayList<>(user.profile);
//        this.profile = Arrays.copyOf(user.profile,user.profile.size(),Profile.class);
        this.active = user.active;
    }

    public User(Integer iduser, String username, String password, String name, String email, String cellphone, String sex, String photo, String identificationcard, String socialsecuritynumber, List<Profile> profile, Boolean active) {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.sex = sex;
        this.photo = photo;
        this.identificationcard = identificationcard;
        this.socialsecuritynumber = socialsecuritynumber;
        this.profile = profile;
        this.active = active;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIdentificationcard() {
        return identificationcard;
    }

    public void setIdentificationcard(String identificationcard) {
        this.identificationcard = identificationcard;
    }

    public String getSocialsecuritynumber() {
        return socialsecuritynumber;
    }

    public void setSocialsecuritynumber(String socialsecuritynumber) {
        this.socialsecuritynumber = socialsecuritynumber;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.iduser == null && other.iduser != null) || (this.iduser != null && !this.iduser.equals(other.iduser))) {
            return false;
        }
        return true;

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.iduser);
        hash = 19 * hash + Objects.hashCode(this.username);
        hash = 19 * hash + Objects.hashCode(this.password);
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + Objects.hashCode(this.email);
        hash = 19 * hash + Objects.hashCode(this.cellphone);
        hash = 19 * hash + Objects.hashCode(this.sex);
        hash = 19 * hash + Objects.hashCode(this.photo);
        hash = 19 * hash + Objects.hashCode(this.identificationcard);
        hash = 19 * hash + Objects.hashCode(this.socialsecuritynumber);
        hash = 19 * hash + Objects.hashCode(this.profile);
        hash = 19 * hash + Objects.hashCode(this.active);
        return hash;
    }

    public boolean equalsReflection(Object object) {

        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.iduser == null && other.iduser != null) || (this.iduser != null && !this.iduser.equals(other.iduser))) {
            return false;
        }

        return EqualsBuilder.reflectionEquals(this, object);
    }

}
