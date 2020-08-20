/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.autentificacionclient.services.UserServices;
import com.avbravo.autentificacionclient.services.UserServices;
import com.avbravo.jmoordb.mongodb.history.services.ErrorInfoServices;
import com.avbravo.jmoordb.util.JmoordbUtil;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@FacesConverter( forClass=User.class, managed = true)
public class UserConverter implements Converter<User> {

   @Inject
    UserServices userServices;

    @Override
    public User getAsObject(FacesContext fc, UIComponent uic, String string) {
          User user = new User();
        try {
            if (string  == null || string.isEmpty()) {
            return null;
        }
             Optional<User> optional = userServices.findByUsername(string);
                if (optional.isPresent()) {
                    user = optional.get();
                }
        } catch (Exception e) {
               JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return user;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, User t) {
     
       try{
            if (t == null) {

                return "";
            }

            if (t.getIduser()!= null) {

                return t.getIduser().toString();
            } else {

                //JmoordbUtil.warningDialog("No es valido el id ","");
            }

        } catch (Exception e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return "";
    }

}
