/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Secondary;
import com.avbravo.jmoordb.pojos.UserInfo;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class Sistema {

    @Id
    private Integer idsistema;
    @Secondary
    private String sistema;
    private String activo;
   

    public Sistema() {
    }

    public Sistema(Integer idsistema, String sistema, String activo) {
        this.idsistema = idsistema;
        this.sistema = sistema;
        this.activo = activo;
    }

    public Integer getIdsistema() {
        return idsistema;
    }

    public void setIdsistema(Integer idsistema) {
        this.idsistema = idsistema;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

   
  

}
