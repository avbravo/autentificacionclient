/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.Profile;
import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.autentificacionclient.services.ProfileServices;
import com.avbravo.jmoordb.configuration.JmoordbContext;
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

   @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Profile profile = new Profile();

        try {
            System.out.println("---------------Analizando converter.............");
            if (s == null || s.equals("null") || s.equals("")) {
                profile.setIdprofile(0);
                System.out.println(">>>>>>>>>>>>><<< Converte es 0");
            } else {
                System.out.println(">>>>>>>>>>>>><<<< voy a convertirlo a entero " + s);
                Integer idprofile = Integer.parseInt(s);
                /*                
Recuperael user dl context
  Busca en el embebido    
                
                 */
                System.out.println(">>>>>>>>><voy a obtener el user del contecto");
                if ((User) JmoordbContext.get("jmoordb_user") == null) {
                    System.out.println(" >>>> user nop esta en el contexto");
                } else {
                    System.out.println(">>>>>>>>>>><< lo encontro en el contexto voy a buscar");
                    User user = (User) JmoordbContext.get("jmoordb_user");

                    System.out.println(">>>>> procedo con el for");
                    for (Profile p : user.getProfile()) {
                        System.out.println("Comparando " + p.getIdrole() + "  con  " + idprofile);
                        if (p.getIdrole().equals(idprofile)) {
                            System.out.println("Encontro en el embebido");
                            profile = p;
                        }
                    }
                }

            }
        } catch (Exception e) {

            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return profile;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String r = "";
        try {
            System.out.println("@@@@@@@@@@@@@@ llego a getAsSDtring");
            if (o instanceof Profile) {
                System.out.println("@@@@@@@ es un profile");
                Profile profile = (Profile) o;
                System.out.println("@@@@@ instanciare");
                r = String.valueOf(profile.getIdprofile());
                System.out.println("@@@@@ ya lo convertire");
            } else if (o instanceof String) {
                System.out.println("@@@@@@@ es un String");
                r = (String) o;
            }
        } catch (Exception e) {
//            errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        System.out.println("@@@@@@@@@ retorna "+r);
        return r;
    }

}
