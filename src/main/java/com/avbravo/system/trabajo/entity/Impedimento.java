/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.system.trabajo.entity;

import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.jmoordb.anotations.Embedded;
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
public class Impedimento {
 @Id
 private Integer idimpedimento;
 private String impedimento;
 private String descripcion;
 private Boolean ejecutado;
 private Boolean active;
  @Embedded 
 User user;
 
  @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
        Impedimento other = (Impedimento) object;
        if ((this.idimpedimento == null && other.idimpedimento!= null) || (this.idimpedimento!= null && !this.idimpedimento.equals(other.idimpedimento))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
 Impedimento other = (Impedimento) object;
        if ((this.idimpedimento == null && other.idimpedimento != null) || (this.idimpedimento!= null && !this.idimpedimento.equals(other.idimpedimento))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
}
