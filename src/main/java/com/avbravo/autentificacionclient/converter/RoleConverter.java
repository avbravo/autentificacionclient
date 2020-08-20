/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.Role;
import com.avbravo.autentificacionclient.entity.Role;
import com.avbravo.autentificacionclient.services.RoleServices;
import com.avbravo.autentificacionclient.services.RoleServices;
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
@FacesConverter( forClass=Role.class, managed = true)
public class RoleConverter implements Converter<Role> {

    @Inject
    RoleServices roleServices;

    @Override
    public Role getAsObject(FacesContext fc, UIComponent uic, String string) {
          Role role = new Role();
        try {
            if (string  == null || string.isEmpty()) {
            return null;
        }
             Optional<Role> optional = roleServices.findByIdrole(Integer.parseInt(string));
                if (optional.isPresent()) {
                    role = optional.get();
                }
        } catch (Exception e) {
               JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return role;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Role t) {
     
       try{
            if (t == null) {

                return "";
            }

            if (t.getIdrole()!= null) {

                return t.getIdrole().toString();
            } else {

                //JmoordbUtil.warningDialog("No es valido el id ","");
            }

        } catch (Exception e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return "";
    }

}
