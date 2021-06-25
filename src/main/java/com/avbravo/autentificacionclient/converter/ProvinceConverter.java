/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.Province;
import com.avbravo.autentificacionclient.services.ProvinceServices;
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
@FacesConverter(value = "provinceConverter", managed = true)
public class ProvinceConverter implements Converter<Province> {

    @Inject
    private ProvinceServices provinceServices;

    @Override
    public Province getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.isEmpty() || value.equals("null")) {
            return new Province();
        }
        try {
            Optional<Province> optional = provinceServices.findByIdprovince(Integer.parseInt(value));
            if (optional.isPresent()) {
                return optional.get();
            }
            return new Province();
        } catch (NumberFormatException e) {

            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error" + e.getLocalizedMessage(), "Not a valid country."));
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Province value) {
        if (value != null) {
            return String.valueOf(value.getIdprovince());
        } else {
            return null;
        }
    }

}
