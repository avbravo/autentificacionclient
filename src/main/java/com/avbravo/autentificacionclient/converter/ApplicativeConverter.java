/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.Applicative;
import com.avbravo.autentificacionclient.entity.Applicative;
import com.avbravo.autentificacionclient.services.ApplicativeServices;
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
@FacesConverter( forClass=Applicative.class, managed = true)
public class ApplicativeConverter implements Converter<Applicative>{

    @Inject
    ApplicativeServices applicativeServices;



    @Override
    public Applicative getAsObject(FacesContext fc, UIComponent uic, String string) {
          Applicative applicative = new Applicative();
        try {
            if (string  == null || string.isEmpty()) {
            return null;
        }
             Optional<Applicative> optional = applicativeServices.findByIdapplicative(Integer.parseInt(string));
                if (optional.isPresent()) {
                    applicative = optional.get();
                }
        } catch (Exception e) {
               JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return applicative;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Applicative t) {
     
       try{
            if (t == null) {

                return "";
            }

            if (t.getIdapplicative()!= null) {

                return t.getIdapplicative().toString();
            } else {

                //JmoordbUtil.warningDialog("No es valido el id ","");
            }

        } catch (Exception e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return "";
    }

   


}
