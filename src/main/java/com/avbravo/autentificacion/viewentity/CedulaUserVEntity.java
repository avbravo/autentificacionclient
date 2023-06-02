/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.autentificacion.viewentity;

import com.avbravo.autentificacionclient.services.LoggerServices;
import com.avbravo.jmoordb.util.JmoordbUtil;
import javax.inject.Inject;

/**
 *
 * @author Soporte-UTICA
 */
public class CedulaUserVEntity {

    String nombre;
    String cedula;
    @Inject
    LoggerServices loggerServices;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public CedulaUserVEntity(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public CedulaUserVEntity() {
        this.nombre = "";
        this.cedula = "";
    }

    public CedulaUserVEntity(JmoordbEntity jmoordbEntity) {
        try {
            this.cedula = jmoordbEntity.getIdData().toString();
            this.nombre = jmoordbEntity.getValueData().toString();
        } catch (Exception exception) {
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), exception, false);
            
        }

    }

}
