/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.system.trabajo.entity;

import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author avbravo
 */
@Data
@Builder
public class Tarjeta {
 @Id
 private Integer idtarjeta;
 private String titulo;
 private String descripcion;
 private String duracion;
 private Integer estimacion;
 private Date vencimiento;
  @Referenced(collection = "Etiqueta",
            field = "idetiqueta", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.trabajo.repository.EtiquetaRepository")
 private List<Etiqueta> etiqueta;
 
  @Referenced(collection = "Importancia",
            field = "idimportancia", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.trabajo.repository.ImportanciaRepository")
  private Importancia importancia;
  
 @Referenced(collection = "User",
            field = "iduser", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.repository.UserRepository")
    private List<User> user;
  
  @Referenced(collection = "Estado",
            field = "idestado", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.trabajo.repository.EstadoRepository")
  private Estado estado;
  
  @Embedded
    private List<Tarea> tarea;
   
     @Embedded
    private List<Requisito> requisito;
  
   @Embedded
    private List<Impedimento> impedimiento;
      @Embedded
    private List<Comentario> comentario;
  
  
 private Boolean active;

 
  @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
        Tarjeta other = (Tarjeta) object;
        if ((this.idtarjeta == null && other.idtarjeta!= null) || (this.idtarjeta!= null && !this.idtarjeta.equals(other.idtarjeta))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
 Tarjeta other = (Tarjeta) object;
        if ((this.idtarjeta == null && other.idtarjeta != null) || (this.idtarjeta!= null && !this.idtarjeta.equals(other.idtarjeta))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }
    
}
