/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.system.planeacion.entity;

import com.avbravo.jmoordb.anotations.Id;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author avbravo
 */
@Data
@Builder
public class Etiqueta {
 @Id
 private Integer idetiqueta;
 private String etiqueta;
 private String color;
 private Boolean active;

    public Etiqueta() {
    }

    public Etiqueta(Integer idetiqueta, String etiqueta, String color, Boolean active) {
        this.idetiqueta = idetiqueta;
        this.etiqueta = etiqueta;
        this.color = color;
        this.active = active;
    }

 
  @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
        Etiqueta other = (Etiqueta) object;
        if ((this.idetiqueta == null && other.idetiqueta!= null) || (this.idetiqueta!= null && !this.idetiqueta.equals(other.idetiqueta))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
 Etiqueta other = (Etiqueta) object;
        if ((this.idetiqueta == null && other.idetiqueta != null) || (this.idetiqueta!= null && !this.idetiqueta.equals(other.idetiqueta))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
}
