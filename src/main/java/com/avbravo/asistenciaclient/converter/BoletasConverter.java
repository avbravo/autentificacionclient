/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.asistenciaclient.converter;

import com.avbravo.asistenciaclient.entity.Boletas;
import com.avbravo.asistenciaclient.services.BoletasServices;
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
@FacesConverter( forClass=Boletas.class, managed = true)
public class BoletasConverter implements Converter<Boletas> {
    @Inject
    BoletasServices boletasServices;

    @Override
    public Boletas getAsObject(FacesContext fc, UIComponent uic, String string) {
          Boletas boletas = new Boletas();
        try {
            if (string  == null || string.isEmpty()) {
            return null;
        }
             Optional<Boletas> optional = boletasServices.findByIdboleta(Integer.parseInt(string));
                if (optional.isPresent()) {
                    boletas = optional.get();
                }
        } catch (Exception e) {
               JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return boletas;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Boletas t) {
     
       try{
            if (t == null) {

                return "";
            }

            if (t.getIdboleta()!= null) {

                return t.getIdboleta().toString();
            } else {

                //JmoordbUtil.warningDialog("No es valido el id ","");
            }

        } catch (Exception e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return "";
    }

}
