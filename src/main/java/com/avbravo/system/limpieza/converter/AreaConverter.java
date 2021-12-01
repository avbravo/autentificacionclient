/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.system.limpieza.converter;

import com.avbravo.system.limpieza.entity.Area;
import com.avbravo.system.limpieza.services.AreaServices;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@RequestScoped
@Named
public class AreaConverter implements Converter {
    @Inject
    AreaServices areaServices;
  @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        try {
           if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof Area) {
          return String.valueOf(((Area) modelValue).getIdarea());
        } else {
            System.out.println("----------->getAsString");
          throw new ConverterException(new FacesMessage(modelValue + " is not a valid from Converter"));
        }
      } catch (Exception e) {
            System.out.println("--------getAsString () "+e.getLocalizedMessage());
            new FacesMessage("Error en converter Area "+e.getLocalizedMessage());
      }

 return "";
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        Area a = new Area();
        if (areaServices == null) {
            System.out.println("Service is nich");
        }

        if (submittedValue == null || submittedValue.isEmpty()) {
            System.out.println("submitted = nil");
            return null;
        }

        try {
            Optional<Area> optional = areaServices.findByIdarea(Integer.parseInt(submittedValue));
            if (optional.isPresent()) {
                a = optional.get();
            }
            return a;
        } catch (Exception e) {
            System.out.println("====================");
            System.out.println("---> getAsObject" +e.getLocalizedMessage());
            System.out.println("====================");
            throw new ConverterException(new FacesMessage(submittedValue + " is not a valid selecction from Converter"), e);
        }
    }

}

