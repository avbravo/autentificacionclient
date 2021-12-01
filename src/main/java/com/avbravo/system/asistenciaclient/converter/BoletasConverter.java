/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.system.asistenciaclient.converter;

import com.avbravo.system.asistenciaclient.entity.Boletas;
import com.avbravo.system.asistenciaclient.services.BoletasServices;
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
@FacesConverter(value = "boletasConverter", managed = true)
public class BoletasConverter implements Converter<Boletas> {

    @Inject
    private BoletasServices boletasServices;

    @Override
    public Boletas getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.isEmpty() || value.equals("null")) {
            return new Boletas();
        }
        try {
            Optional<Boletas> optional = boletasServices.findByIdboleta(Integer.parseInt(value));
            if (optional.isPresent()) {
                return optional.get();
            }
            return new Boletas();
        } catch (NumberFormatException e) {

            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error" + e.getLocalizedMessage(), "Not a valid country."));
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Boletas value) {
        if (value != null) {
            return String.valueOf(value.getIdboleta());
        } else {
            return null;
        }
    }

}
