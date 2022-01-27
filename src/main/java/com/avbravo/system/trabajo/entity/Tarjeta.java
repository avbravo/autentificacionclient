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
 private Date inicio;
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
  
  @Referenced(collection = "Columna",
            field = "idcolumna", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.trabajo.repository.ColumnaRepository")
  private Columna columna;
  @Embedded
  private List<Tarea> tarea;
  @Embedded
  private List<Impedimento> impedimento;
  @Embedded
  private List<Comentario> comentario;
  
  private Boolean active;
  
  
  

    public Tarjeta() {
    }

    public Tarjeta(Integer idtarjeta, String titulo, String descripcion, Date inicio, Date vencimiento, List<Etiqueta> etiqueta, Importancia importancia, List<User> user, Columna columna, List<Tarea> tarea, List<Impedimento> impedimento, List<Comentario> comentario, Boolean active) {
        this.idtarjeta = idtarjeta;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.inicio = inicio;
        this.vencimiento = vencimiento;
        this.etiqueta = etiqueta;
        this.importancia = importancia;
        this.user = user;
        this.columna = columna;
        this.tarea = tarea;
        this.impedimento = impedimento;
        this.comentario = comentario;
        this.active = active;
    }

   
  
  
  
  

 
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
