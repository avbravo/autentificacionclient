/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.Profile;
import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.jmoordb.configuration.JmoordbContext;
import com.avbravo.jmoordb.util.JmoordbUtil;
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
@FacesConverter( forClass=Profile.class, managed = true)
public class ProfileConverter implements Converter<Profile> {

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Profile t) {
        try {

            if (t == null) {

                return "";
            }

            if (t.getIdprofile() != null) {

                return t.getIdprofile().toString();
            } else {

                //JmoordbUtil.warningDialog("No es valido el id ","");
            }

        } catch (Exception e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public Profile getAsObject(FacesContext fc, UIComponent uic, String string) {
        Profile profile = new Profile();
        try {

            if (string == null || string.isEmpty()) {

                return null;
            }

            //Busca el profile en el documento embedbido
            Integer idprofile = Integer.parseInt(string);

            if ((User) JmoordbContext.get("jmoordb_user") == null) {
               
            } else {

                User user = (User) JmoordbContext.get("jmoordb_user");

                for (Profile p : user.getProfile()) {

                    if (p.getIdprofile().equals(idprofile)) {

                        profile = p;
                    }
                }
                return profile;
            }
        } catch (NumberFormatException e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return profile;
    }

}
