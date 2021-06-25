/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.siuclient.converter;

import com.avbravo.siuclient.entity.Estudiante;
import com.avbravo.siuclient.services.EstudianteServices;
import java.util.Optional;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@FacesConverter(value = "estudianteConverter", managed = true)
public class EstudianteConverter implements Converter<Estudiante> {

    @Inject
    private EstudianteServices estudianteServices;

    @Override
    public Estudiante getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.isEmpty() || value.equals("null")) {
            return new Estudiante();
        }
        try {
            Optional<Estudiante> optional = estudianteServices.findByIdestudiante(Integer.parseInt(value));
            if (optional.isPresent()) {
                return optional.get();
            }
            return new Estudiante();
        } catch (NumberFormatException e) {

            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error" + e.getLocalizedMessage(), "Not a valid country."));
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Estudiante value) {
        if (value != null) {
            return String.valueOf(value.getIdestudiante());
        } else {
            return null;
        }
    }

}
