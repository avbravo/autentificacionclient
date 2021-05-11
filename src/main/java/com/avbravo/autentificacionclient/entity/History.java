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
public class History {

    @Id
    private Integer idhistory;
    private String collection;
    private String id;
    private String event;
    private String description;
    private String content;
    private String username;
    private Date dateevent;
    private Integer idapplicative;
    private String databasename;

    public History() {
    }

    public History(Integer idhistory, String collection, String id, String event, String description, String content, String username, Date dateevent, Integer idapplicative, String databasename) {
        this.idhistory = idhistory;
        this.collection = collection;
        this.id = id;
        this.event = event;
        this.description = description;
        this.content = content;
        this.username = username;
        this.dateevent = dateevent;
        this.idapplicative = idapplicative;
        this.databasename = databasename;
    }

    public Integer getIdhistory() {
        return idhistory;
    }

    public void setIdhistory(Integer idhistory) {
        this.idhistory = idhistory;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateevent() {
        return dateevent;
    }

    public void setDateevent(Date dateevent) {
        this.dateevent = dateevent;
    }

    public Integer getIdapplicative() {
        return idapplicative;
    }

    public void setIdapplicative(Integer idapplicative) {
        this.idapplicative = idapplicative;
    }

    public String getDatabasename() {
        return databasename;
    }

    public void setDatabasename(String databasename) {
        this.databasename = databasename;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        if ((this.idhistory == null && other.idhistory != null) || (this.idhistory != null && !this.idhistory.equals(other.idhistory))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        if ((this.idhistory == null && other.idhistory != null) || (this.idhistory != null && !this.idhistory.equals(other.idhistory))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }


    public static class Builder {

        private Integer idhistory;
        private String collection;
        private String id;
        private String event;
        private String description;
        private String content;
        private String username;
        private Date dateevent;
        private Integer idapplicative;
        private String databasename;

         public Builder idhistory(Integer idhistory) {
            this.idhistory = idhistory;
            return this;
        }
         public Builder collection(String collection) {
            this.collection = collection;
            return this;
        }
         public Builder id(String id) {
            this.id = id;
            return this;
        }
         public Builder event(String event) {
            this.event= event;
            return this;
        }
         public Builder description(String description) {
            this.description= description;
            return this;
        }
         public Builder content(String content) {
            this.content= content;
            return this;
        }
         public Builder username(String username) {
            this.username= username;
            return this;
        }
         public Builder dateevent(Date dateevent) {
            this.dateevent= dateevent;
            return this;
        }
         
          public Builder idapplicative(Integer idapplicative) {
            this.idapplicative= idapplicative;
            return this;
        }
         
          public Builder databasename(String databasename) {
            this.databasename= databasename;
            return this;
        }
          public History build() {
            return new History(idhistory, collection, id, event, description, content, username, dateevent, idapplicative, databasename);

        }
         
         
    }

}
