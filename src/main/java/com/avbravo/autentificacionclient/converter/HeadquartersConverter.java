/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.Headquarters;
import com.avbravo.autentificacionclient.services.HeadquartersServices;
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
@FacesConverter( forClass=Headquarters.class, managed = true)
public class HeadquartersConverter implements Converter<Headquarters> {

    @Inject
    HeadquartersServices headquartersServices;

    @Override
    public Headquarters getAsObject(FacesContext fc, UIComponent uic, String string) {
          Headquarters headquarters = new Headquarters();
        try {
            if (string  == null || string.isEmpty()) {
            return null;
        }
             Optional<Headquarters> optional = headquartersServices.findByIdheadquarters(Integer.parseInt(string));
                if (optional.isPresent()) {
                    headquarters = optional.get();
                }
        } catch (Exception e) {
               JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return headquarters;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Headquarters t) {
     
       try{
            if (t == null) {

                return "";
            }

            if (t.getIdheadquarters()!= null) {

                return t.getIdheadquarters().toString();
            } else {

                //JmoordbUtil.warningDialog("No es valido el id ","");
            }

        } catch (Exception e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return "";
    }

}
