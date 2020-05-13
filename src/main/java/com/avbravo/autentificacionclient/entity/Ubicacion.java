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
 * @author avbravo Los campos enteros pueden ser con un autoincrementable o
 * insertando la fechahoradelsistema en milisegundos.
 */
public class Ubicacion {

    private Integer idubicacion;

    @Referenced(collection = "Sistema",
            field = "idsistema", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.SistemaRepository")
    Sistema sistema;
    
    @Referenced(collection = "Perfil",
            field = "idperfil", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.PerfilRepository")
    Perfil perfil;

    public Ubicacion() {
    }

    public Ubicacion(Integer idubicacion, Sistema sistema, Perfil perfil) {
        this.idubicacion = idubicacion;
        this.sistema = sistema;
        this.perfil = perfil;
    }

    public Integer getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(Integer idubicacion) {
        this.idubicacion = idubicacion;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

   
    
    
    
   
}
