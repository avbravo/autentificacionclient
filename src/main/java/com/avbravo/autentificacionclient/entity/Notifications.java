/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Id;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author avbravo
 */
public class Notifications {

    @Id
    private Integer idnotifications;
    private String username;
    private String message;
    private String event;
    private Date dateevent;
    private Boolean viewed;
    private Integer idapplicative;

    public Notifications() {
    }

    public Notifications(Integer idnotifications, String username, String message, String event, Date dateevent, Boolean viewed, Integer idapplicative) {
        this.idnotifications = idnotifications;
        this.username = username;
        this.message = message;
        this.event = event;
        this.dateevent = dateevent;
        this.viewed = viewed;
        this.idapplicative = idapplicative;
    }

   

    public Integer getIdnotifications() {
        return idnotifications;
    }

    public void setIdnotifications(Integer idnotifications) {
        this.idnotifications = idnotifications;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getDateevent() {
        return dateevent;
    }

    public void setDateevent(Date dateevent) {
        this.dateevent = dateevent;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }

  

    public Integer getIdapplicative() {
        return idapplicative;
    }

    public void setIdapplicative(Integer idapplicative) {
        this.idapplicative = idapplicative;
    }
    
    
    

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Notifications)) {
            return false;
        }
        Notifications other = (Notifications) object;
        if ((this.idnotifications == null && other.idnotifications != null) || (this.idnotifications != null && !this.idnotifications.equals(other.idnotifications))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Notifications)) {
            return false;
        }
        Notifications other = (Notifications) object;
        if ((this.idnotifications == null && other.idnotifications != null) || (this.idnotifications != null && !this.idnotifications.equals(other.idnotifications))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }

    public static class Builder {

        private Integer idnotifications;
        private String username;
        private String message;
        private String event;
        private Date dateevent;
        private Boolean viewed;
        private Integer idapplicative;

        public Builder idnotifications(Integer idnotifications) {
            this.idnotifications = idnotifications;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder event(String event) {
            this.event = event;
            return this;
        }

        public Builder viewed(Boolean viewed) {
            this.viewed = viewed;
            return this;
        }

        public Builder dateevent(Date dateevent) {
            this.dateevent = dateevent;
            return this;
        }

        public Builder idapplicative(Integer idapplicative) {
            this.idapplicative = idapplicative;
            return this;
        }

        public Notifications build() {
            return new Notifications(idnotifications, username, message, event, dateevent, viewed, idapplicative);

        }

    }

}


