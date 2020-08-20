/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.Departament;
import com.avbravo.autentificacionclient.services.DepartamentServices;
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
@FacesConverter( forClass=Departament.class, managed = true)
public class DepartamentConverter implements Converter<Departament> { 
    @Inject
    DepartamentServices departamentServices;

    @Override
    public Departament getAsObject(FacesContext fc, UIComponent uic, String string) {
          Departament departament = new Departament();
        try {
            if (string  == null || string.isEmpty()) {
            return null;
        }
             Optional<Departament> optional = departamentServices.findByIddepartament(Integer.parseInt(string));
                if (optional.isPresent()) {
                    departament = optional.get();
                }
        } catch (Exception e) {
               JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return departament;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Departament t) {
     
       try{
            if (t == null) {

                return "";
            }

            if (t.getIddepartament()!= null) {

                return t.getIddepartament().toString();
            } else {

                //JmoordbUtil.warningDialog("No es valido el id ","");
            }

        } catch (Exception e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return "";
    }

}