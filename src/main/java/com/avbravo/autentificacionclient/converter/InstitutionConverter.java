/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.Institution;
import com.avbravo.autentificacionclient.services.InstitutionServices;
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
@FacesConverter( forClass=Institution.class, managed = true)
public class InstitutionConverter implements Converter<Institution> {

    @Inject
    InstitutionServices institutionServices;

    @Override
    public Institution getAsObject(FacesContext fc, UIComponent uic, String string) {
          Institution institution = new Institution();
        try {
            if (string  == null || string.isEmpty()) {
            return null;
        }
             Optional<Institution> optional = institutionServices.findByIdinstitution(Integer.parseInt(string));
                if (optional.isPresent()) {
                    institution = optional.get();
                }
        } catch (Exception e) {
               JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return institution;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Institution t) {
     
       try{
            if (t == null) {

                return "";
            }

            if (t.getIdinstitution()!= null) {

                return t.getIdinstitution().toString();
            } else {

                //JmoordbUtil.warningDialog("No es valido el id ","");
            }

        } catch (Exception e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return "";
    }


}
