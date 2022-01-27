/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.system.trabajo.converter;

import com.avbravo.system.trabajo.entity.Proyecto;
import com.avbravo.system.trabajo.services.ProyectoServices;
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
public class ProyectoConverter implements Converter {
    @Inject
    ProyectoServices proyectoServices;
  @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        try {
           if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof Proyecto) {
          return String.valueOf(((Proyecto) modelValue).getIdproyecto());
        } else {
            System.out.println("----------->getAsString");
          throw new ConverterException(new FacesMessage(modelValue + " is not a valid from Converter"));
        }
      } catch (Exception e) {
            System.out.println("--------getAsString () "+e.getLocalizedMessage());
            new FacesMessage("Error en converter Proyecto "+e.getLocalizedMessage());
      }

 return "";
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        Proyecto a = new Proyecto();
        if (proyectoServices == null) {
            System.out.println("Service is nich");
        }

        if (submittedValue == null || submittedValue.isEmpty()) {
            System.out.println("submitted = nil");
            return null;
        }

        try {
            Optional<Proyecto> optional = proyectoServices.findByIdproyecto(Integer.parseInt(submittedValue));
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

