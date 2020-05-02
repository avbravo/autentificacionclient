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
 
 */
public class Sede {
 private Integer idsede;
  private String sede;
 
    @Referenced(collection = "Institucion",
            field = "idinstitucion", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.InstitucionRepository")
           Institucion institucion;
    
     private String activo;
          @Embedded
    List<UserInfo> userInfo;

  

    public Sede() {
    }

    public Sede(Integer idsede, String sede, Institucion institucion, String activo, List<UserInfo> userInfo) {
        this.idsede = idsede;
        this.sede = sede;
        this.institucion = institucion;
        this.activo = activo;
        this.userInfo = userInfo;
    }

    public Integer getIdsede() {
        return idsede;
    }

    public void setIdsede(Integer idsede) {
        this.idsede = idsede;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
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

   
     
  

}
