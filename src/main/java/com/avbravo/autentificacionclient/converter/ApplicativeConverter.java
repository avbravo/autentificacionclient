/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

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
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@RequestScoped
public class ApplicativeConverter implements Converter {

    @Inject
    ErrorInfoServices errorServices;
    @Inject
    ApplicativeServices applicativeServices;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Applicative applicative = new Applicative();
        try {
            if (!s.equals("null")) {
                
                Optional<Applicative> optional = applicativeServices.findByIdapplicative(Integer.parseInt(s));
                if (optional.isPresent()) {
                    applicative = optional.get();
                }
            }
        } catch (Exception e) {
            errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
        }
        return applicative;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String r = "";
        try {
            if (o instanceof Applicative) {
                Applicative applicative = (Applicative) o;
                r = String.valueOf(applicative.getIdapplicative());
            } else if (o instanceof String) {
                r = (String) o;
            }
        } catch (Exception e) {
            errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
        }
        return r;
    }

}
