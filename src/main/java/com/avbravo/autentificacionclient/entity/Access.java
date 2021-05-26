/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 *
 * @author avbravo
 */
public class Access {
     @Id
    private Integer idaccess;
    private String username;
    private String event;
    private String ip;
    private Date date;
    private Integer idprofile;
    private Integer idapplicative;
    private Integer idrole;
    private Integer iddepartament;

    public Access() {
    }

    public Access(Integer idaccess, String username, String event, String ip, Date date, Integer idprofile, Integer idapplicative, Integer idrole, Integer iddepartament) {
        this.idaccess = idaccess;
        this.username = username;
        this.event = event;
        this.ip = ip;
        this.date = date;
        this.idprofile = idprofile;
        this.idapplicative = idapplicative;
        this.idrole = idrole;
        this.iddepartament = iddepartament;
    }

    
    
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

  
    
    
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIdprofile() {
        return idprofile;
    }

    public void setIdprofile(Integer idprofile) {
        this.idprofile = idprofile;
    }

    public Integer getIdapplicative() {
        return idapplicative;
    }

    public void setIdapplicative(Integer idapplicative) {
        this.idapplicative = idapplicative;
    }

    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    public Integer getIddepartament() {
        return iddepartament;
    }

    public void setIddepartament(Integer iddepartament) {
        this.iddepartament = iddepartament;
    }

    public Integer getIdaccess() {
        return idaccess;
    }

    public void setIdaccess(Integer idaccess) {
        this.idaccess = idaccess;
    }

   

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Applicative)) {
            return false;
        }
        Access other = (Access) object;
        if ((this.idaccess == null && other.idaccess != null) || (this.idaccess != null && !this.idaccess.equals(other.idaccess))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Applicative)) {
            return false;
        }
        Access other = (Access) object;
        if ((this.idaccess == null && other.idaccess != null) || (this.idaccess != null && !this.idaccess.equals(other.idaccess))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }

    
      public static class Builder {

     private Integer idaccess;
    private String username;
    private String event;
    private String ip;
    private Date date;
    private Integer idprofile;
    private Integer idapplicative;
    private Integer idrole;
    private Integer iddepartament;

         public Builder idaccess(Integer idaccess) {
            this.idaccess = idaccess;
            return this;
        }
         public Builder username(String username) {
            this.username = username;
            return this;
        }
         public Builder ip(String ip) {
            this.ip = ip;
            return this;
        }
         public Builder date(Date date) {
            this.date = date;
            return this;
        }
         public Builder event(String event) {
            this.event= event;
            return this;
        }
         public Builder idprofile(Integer idprofile) {
            this.idprofile= idprofile;
            return this;
        }
         public Builder idapplicative(Integer idapplicative) {
            this.idapplicative= idapplicative;
            return this;
        }
         public Builder idrole(Integer idrole) {
            this.idrole= idrole;
            return this;
        }
         public Builder iddepartament(Integer iddepartament) {
            this.iddepartament= iddepartament;
            return this;
        }
       
      
          public Access build() {
            return new Access(idaccess, username, event, ip, date, idprofile, idapplicative, idrole, iddepartament);

        }
         
         
    }
}

         
