/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.Access;
import com.avbravo.autentificacionclient.services.AccessServices;
import com.avbravo.jmoordb.util.JmoordbUtil;
import java.util.Optional;
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
@FacesConverter( forClass=Access.class, managed = true)
public class AccessConverter implements Converter<Access> {
    @Inject
    AccessServices accessServices;

    @Override
    public Access getAsObject(FacesContext fc, UIComponent uic, String string) {
          Access access = new Access();
        try {
            if (string  == null || string.isEmpty()) {
            return null;
        }
             Optional<Access> optional = accessServices.findByIdaccess(Integer.parseInt(string));
                if (optional.isPresent()) {
                    access = optional.get();
                }
        } catch (Exception e) {
               JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return access;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Access t) {
     
       try{
            if (t == null) {

                return "";
            }

            if (t.getIdaccess()!= null) {

                return t.getIdaccess().toString();
            } else {

                //JmoordbUtil.warningDialog("No es valido el id ","");
            }

        } catch (Exception e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return "";
    }

}
