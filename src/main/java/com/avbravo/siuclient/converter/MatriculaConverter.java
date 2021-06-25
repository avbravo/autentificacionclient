/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.siuclient.converter;

import com.avbravo.siuclient.entity.Matricula;
import com.avbravo.siuclient.services.MatriculaServices;
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
@FacesConverter(value = "matriculaConverter", managed = true)
public class MatriculaConverter implements Converter<Matricula> {

    @Inject
    private MatriculaServices matriculaServices;

    @Override
    public Matricula getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.isEmpty() || value.equals("null")) {
            return new Matricula();
        }
        try {
            Optional<Matricula> optional = matriculaServices.findByIdmatricula(Integer.parseInt(value));
            if (optional.isPresent()) {
                return optional.get();
            }
            return new Matricula();
        } catch (NumberFormatException e) {

            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error" + e.getLocalizedMessage(), "Not a valid country."));
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Matricula value) {
        if (value != null) {
            return String.valueOf(value.getIdmatricula());
        } else {
            return null;
        }
    }

}
