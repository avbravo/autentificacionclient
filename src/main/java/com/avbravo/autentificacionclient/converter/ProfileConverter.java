/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.Profile;
import com.avbravo.autentificacionclient.services.ProfileServices;
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
public class ProfileConverter implements Converter {

    @Inject
    ErrorInfoServices errorServices;
    @Inject
    ProfileServices profileServices;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Profile profile = new Profile();
        try {
            if (!s.equals("null")) {
              profile.setIdprofile(Integer.parseInt(s));
//                Optional<Profile> optional = profileServices.findByIdprofile(Integer.parseInt(s));
//                if (optional.isPresent()) {
//                    profile = optional.get();
//                }
            }
        } catch (Exception e) {
//            errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
          JmoordbUtil.errorDialog( JmoordbUtil.nameOfMethod(),e.getLocalizedMessage());
        }
        return profile;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String r = "";
        try {
            if (o instanceof Profile) {
                Profile profile = (Profile) o;
                r = String.valueOf(profile.getIdprofile());
            } else if (o instanceof String) {
                r = (String) o;
            }
        } catch (Exception e) {
             JmoordbUtil.errorDialog( JmoordbUtil.nameOfMethod(),e.getLocalizedMessage());
//            errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
        }
        return r;
    }

}
