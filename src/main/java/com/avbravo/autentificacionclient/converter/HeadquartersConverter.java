/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.Headquarters;
import com.avbravo.autentificacionclient.services.HeadquartersServices;
import com.avbravo.jmoordb.mongodb.history.services.ErrorInfoServices;
import com.avbravo.jmoordb.util.JmoordbUtil;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@RequestScoped
public class HeadquartersConverter implements Converter {

    @Inject
    ErrorInfoServices errorServices;
    @Inject
    HeadquartersServices headquartersServices;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Headquarters headquarters = new Headquarters();
        try {
            if (!s.equals("null")) {
                
                Optional<Headquarters> optional = headquartersServices.findByIdheadquarters(Integer.parseInt(s));
                if (optional.isPresent()) {
                    headquarters = optional.get();
                }
            }
        } catch (Exception e) {
            errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
        }
        return headquarters;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String r = "";
        try {
            if (o instanceof Headquarters) {
                Headquarters headquarters = (Headquarters) o;
                r = String.valueOf(headquarters.getIdheadquarters());
            } else if (o instanceof String) {
                r = (String) o;
            }
        } catch (Exception e) {
            errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
        }
        return r;
    }

}
