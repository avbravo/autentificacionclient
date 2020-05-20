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
public class Perfil {

    @Id
    private Integer idperfil;

    @Referenced(collection = "Sistema",
            field = "idsistema", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.SistemaRepository")
    Sistema sistema;
    @Referenced(collection = "Rol",
            field = "idrol", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.RolRepository")
    List<Rol> rol;

    public Perfil() {
    }

    public Perfil(Integer idperfil, Sistema sistema, List<Rol> rol) {
        this.idperfil = idperfil;
        this.sistema = sistema;
        this.rol = rol;
    }

    public Integer getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Integer idperfil) {
        this.idperfil = idperfil;
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

}
