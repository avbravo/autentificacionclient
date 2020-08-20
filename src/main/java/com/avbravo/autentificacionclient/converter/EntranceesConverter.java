/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.Entrancees;
import com.avbravo.autentificacionclient.services.EntranceesServices;
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
@FacesConverter( forClass=Entrancees.class, managed = true) 
public class EntranceesConverter implements Converter<Entrancees> {
    @Inject
    EntranceesServices entranceesServices;

    @Override
    public Entrancees getAsObject(FacesContext fc, UIComponent uic, String string) {
          Entrancees entrancees = new Entrancees();
        try {
            if (string  == null || string.isEmpty()) {
            return null;
        }
             Optional<Entrancees> optional = entranceesServices.findByIdentrancees(Integer.parseInt(string));
                if (optional.isPresent()) {
                    entrancees = optional.get();
                }
        } catch (Exception e) {
               JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return entrancees;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Entrancees t) {
     
       try{
            if (t == null) {

                return "";
            }

            if (t.getIdentrancees()!= null) {

                return t.getIdentrancees().toString();
            } else {

                //JmoordbUtil.warningDialog("No es valido el id ","");
            }

        } catch (Exception e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return "";
    }

}
