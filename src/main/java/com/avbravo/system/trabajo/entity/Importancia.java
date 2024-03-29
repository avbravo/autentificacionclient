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
public class Importancia {
 @Id
 private Integer idimportancia;
 private String importancia;
 private Boolean active;

 
  @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
        Importancia other = (Importancia) object;
        if ((this.idimportancia == null && other.idimportancia!= null) || (this.idimportancia!= null && !this.idimportancia.equals(other.idimportancia))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
 Importancia other = (Importancia) object;
        if ((this.idimportancia == null && other.idimportancia != null) || (this.idimportancia!= null && !this.idimportancia.equals(other.idimportancia))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
}
