/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.autentificacionclient.services.UserServices;
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
public class UserConverter implements Converter {

    @Inject
    UserServices userService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof User) {
  //          return String.valueOf(((User) modelValue).getIduser());
          return String.valueOf(((User) modelValue).getUsername());
        } else {
            throw new ConverterException(new FacesMessage(modelValue + " is not a valid User"));
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
       // System.out.println("submitted: " + Long.valueOf(submittedValue));
        System.out.println("submitted: " +submittedValue);
        User a = new User();
        if (userService == null) {
            System.out.println("Service is nich");
        }

        if (submittedValue == null || submittedValue.isEmpty()) {
            System.out.println("submitted = nil");
            return null;
        }
        System.out.println("leggie al 57");
        try {
            //return em.find(UserEntity.class, Long.valueOf(submittedValue));
            System.out.println("llegue");
            Optional<User> optional = userService.findByUsername(submittedValue);
            if (optional.isPresent()) {
                a = optional.get();
            }
            return a;
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(submittedValue + " is not a valid User ID"), e);
        }
    }
}
