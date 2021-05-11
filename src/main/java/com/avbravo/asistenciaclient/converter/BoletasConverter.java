/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.asistenciaclient.converter;

import com.avbravo.asistenciaclient.entity.Boletas;
import com.avbravo.asistenciaclient.services.BoletasServices;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;
/**
 *
 * @author avbravo
 */
@RequestScoped
@Named
public class BoletasConverter implements Converter{
    @Inject
    BoletasServices boletasServices;

   @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }
        if (modelValue instanceof Boletas) {
          return String.valueOf(((Boletas) modelValue).getIdboleta());
        } else {
            throw new ConverterException(new FacesMessage(modelValue + " is not a valid from Converter"));
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        Boletas a = new Boletas();
        if (boletasServices == null) {
            System.out.println("Service is nich");
        }

        if (submittedValue == null || submittedValue.isEmpty()) {
            System.out.println("submitted = nil");
            return null;
        }

        try {
            Optional<Boletas> optional = boletasServices.findByIdboleta(Integer.parseInt(submittedValue));
            if (optional.isPresent()) {
                a = optional.get();
            }
            return a;
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(submittedValue + " is not a valid selecction from Converter"), e);
        }
    }

}
