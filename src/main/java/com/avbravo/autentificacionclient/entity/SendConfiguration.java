/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author avbravo
 */
public class SendConfiguration {
     @Id
    private Integer idsendconfiguration;
   @Referenced(collection = "Applicative",
            field = "idapplicative", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.ApplicativeRepository")
    private Applicative applicative;
    private String module;
    private String event;
 
    private Boolean active;
    @Embedded
    List<Profile> profile;

    public SendConfiguration() {
    }

    public SendConfiguration(Integer idsendconfiguration, Applicative applicative, String module, String event, Boolean active, List<Profile> profile) {
        this.idsendconfiguration = idsendconfiguration;
        this.applicative = applicative;
        this.module = module;
        this.event = event;
        this.active = active;
        this.profile = profile;
    }

  
    
    
    
    public Integer getIdsendconfiguration() {
        return idsendconfiguration;
    }

    public void setIdsendconfiguration(Integer idsendconfiguration) {
        this.idsendconfiguration = idsendconfiguration;
    }

    public Applicative getApplicative() {
        return applicative;
    }

    public void setApplicative(Applicative applicative) {
        this.applicative = applicative;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Profile> getProfile() {
        return profile;
    }

    public void setProfile(List<Profile> profile) {
        this.profile = profile;
    }

   
    
   
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SendConfiguration)) {
            return false;
        }
        SendConfiguration other = (SendConfiguration) object;
        if ((this.idsendconfiguration == null && other.idsendconfiguration!= null) || (this.idsendconfiguration!= null && !this.idsendconfiguration.equals(other.idsendconfiguration))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof SendConfiguration)) {
            return false;
        }
    SendConfiguration other = (SendConfiguration) object;
        if ((this.idsendconfiguration == null && other.idsendconfiguration != null) || (this.idsendconfiguration!= null && !this.idsendconfiguration.equals(other.idsendconfiguration))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
    
    
}
