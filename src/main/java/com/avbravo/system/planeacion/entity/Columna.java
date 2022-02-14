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
public class Columna {
 @Id
 private Integer idcolumna;
 private String columna;
 private Boolean active;

    public Columna() {
    }

    public Columna(Integer idcolumna, String columna, Boolean active) {
        this.idcolumna = idcolumna;
        this.columna = columna;
        this.active = active;
    }

 
 
 
  @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
        Columna other = (Columna) object;
        if ((this.idcolumna == null && other.idcolumna!= null) || (this.idcolumna!= null && !this.idcolumna.equals(other.idcolumna))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
 Columna other = (Columna) object;
        if ((this.idcolumna == null && other.idcolumna != null) || (this.idcolumna!= null && !this.idcolumna.equals(other.idcolumna))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
}
