/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.Usuario;
import com.avbravo.autentificacionclient.services.UsuarioServices;
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
public class UsuarioConverter implements Converter {

    @Inject
    ErrorInfoServices errorServices;
    @Inject
    UsuarioServices userServices;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Usuario user = new Usuario();
        try {
            if (!s.equals("null")) {
                Usuario b = new Usuario();
                b.setUsername(s);
                Optional<Usuario> optional = userServices.findByUsername(s);
                if (optional.isPresent()) {
                    user = optional.get();
                }
            }
        } catch (Exception e) {
            errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
        }
        return user;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String r = "";
        try {
            if (o instanceof Usuario) {
                Usuario user = (Usuario) o;
                r = String.valueOf(user.getUsername());
            } else if (o instanceof String) {
                r = (String) o;
            }
        } catch (Exception e) {
            errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
        }
        return r;
    }

}
