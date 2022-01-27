/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.system.trabajo.entity;

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
public class Estado {
 @Id
 private Integer idestado;
 private String estado;
 private Boolean active;

 
  @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.idestado == null && other.idestado!= null) || (this.idestado!= null && !this.idestado.equals(other.idestado))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
 Estado other = (Estado) object;
        if ((this.idestado == null && other.idestado != null) || (this.idestado!= null && !this.idestado.equals(other.idestado))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
}
