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

/**
 *
 * @author avbravo
 * Los campos enteros pueden ser con un autoincrementable o insertando la fechahoradelsistema en milisegundos.
 */
public class Profile {
 private Integer idprofile;
 
    @Referenced(collection = "Sistema",
            field = "idsistema", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.SistemaRepository")
           Sistema sistema;
    @Referenced(collection = "Rol",
            field = "idrol", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.RolRepository")
           List<Rol> rol;
    
          @Embedded
    List<UserInfo> userInfo;

  

    public Profile() {
    }

    public Profile(Integer idprofile, Sistema sistema, List<Rol> rol, List<UserInfo> userInfo) {
        this.idprofile = idprofile;
        this.sistema = sistema;
        this.rol = rol;
        this.userInfo = userInfo;
    }

    
    
    
    
    public Integer getIdprofile() {
        return idprofile;
    }

    public void setIdprofile(Integer idprofile) {
        this.idprofile = idprofile;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public List<Rol> getRol() {
        return rol;
    }

    public void setRol(List<Rol> rol) {
        this.rol = rol;
    }

    public List<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }

  
   
  

}
