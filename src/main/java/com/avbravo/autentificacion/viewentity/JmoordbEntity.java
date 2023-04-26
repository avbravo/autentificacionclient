/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.autentificacion.viewentity;

import com.avbravo.jmoordb.pojos.JmoordbResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Soporte-UTICA
 */
public class JmoordbEntity {

    private String idData;
    private String valueData;

    public String getIdData() {
        return idData;
    }

    public void setIdData(String idData) {
        this.idData = idData;
    }

    public String getValueData() {
        return valueData;
    }

    public void setValueData(String valueData) {
        this.valueData = valueData;
    }

    public JmoordbEntity(String nombreUsuario, String cantidadBoletas) {
        this.idData = nombreUsuario;
        this.valueData = cantidadBoletas;
    }

    public JmoordbEntity() {
        this.idData = "";
        this.valueData = "";
    }

    public JmoordbEntity(JmoordbResult origen) {
        if (origen != null) {

            if (origen.get("cantidad") != null) {
                try {
                    this.idData = origen.get("_id");
                    this.valueData = origen.get("cantidad");

                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }
            }
        }
    }

}
