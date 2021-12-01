/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.system.siuclient.entity;

import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author avbravo
 */
public class Matricula {

    @Id
    private Integer idmatricula;
    @Min(value = 0,message = "Valor minimo cero")
    private Integer anio;
    @NotNull(message="Grupo no debe ser nulo")
    private String grupo;
    @NotNull
    private String aula;
    @Min(value = 0,message = "Valor minimo cero")
    private Integer capacidad;
     @Min(value = 0,message = "Valor minimo cero")
    private Integer matriculados;

    @Referenced(collection = "Estudiante",
            field = "idestudiante", javatype = "Integer", lazy = false,
            repository = "com.avbravo.autentificacion.siu.repository.EstudianteRepository")
    private List<Estudiante> estudiante;

    private Boolean active;

    public Matricula() {
    }

    public Matricula(Integer idmatricula, Integer anio, String grupo, String aula, Integer capacidad, Integer matriculados, List<Estudiante> estudiante, Boolean active) {
        this.idmatricula = idmatricula;
        this.anio = anio;
        this.grupo = grupo;
        this.aula = aula;
        this.capacidad = capacidad;
        this.matriculados = matriculados;
        this.estudiante = estudiante;
        this.active = active;
    }

    public Integer getIdmatricula() {
        return idmatricula;
    }

    public void setIdmatricula(Integer idmatricula) {
        this.idmatricula = idmatricula;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getMatriculados() {
        return matriculados;
    }

    public void setMatriculados(Integer matriculados) {
        this.matriculados = matriculados;
    }

    public List<Estudiante> getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(List<Estudiante> estudiante) {
        this.estudiante = estudiante;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
   @Override
    public boolean equals(Object object) {
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.idmatricula == null && other.idmatricula!= null) || (this.idmatricula!= null && !this.idmatricula.equals(other.idmatricula))) {
            return false;
        }
        return true;
    }

    public boolean equalsReflection(Object object) {
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.idmatricula == null && other.idmatricula != null) || (this.idmatricula!= null && !this.idmatricula.equals(other.idmatricula))) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, object);
    }

}
