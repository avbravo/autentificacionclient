/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.autentificacionclient.services.UserServices;
import com.avbravo.jmoordb.configuration.JmoordbContext;
import com.avbravo.jmoordb.util.JmoordbUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.naming.InitialContext;

/**
 *
 * @author avbravo
 */
@Named
@FacesConverter(forClass = User.class, managed = true)
public class UserConverter implements Converter<User> {

    //@Inject
    private UserServices userServices;
    private InitialContext ic;

    @Override
    public User getAsObject(FacesContext fc, UIComponent uic, String string) {
        User user = new User();
        try {
            System.out.println("---------------------------------");
            if (string == null || string.isEmpty()) {
                System.out.println("+++++++++ getAsObject es null");
                return null;
            }
            System.out.println(">>>>>>>>>> obtengo el ic");
            ic = new InitialContext();

//            String myModuleName = (String) ic.lookup("java:module/ModuleName");
//            String myApplicationName = (String) ic.lookup("java:app/AppName");

            System.out.println("%%%%%  AQUI OBTENGO EL SERVICES--------> del lookup");
          UserServices userServices2  = (UserServices)ic.lookup("java:module/UserServices");
            
//            userServices = (UserServices) ic.lookup("Java:global/com.mycom.rentalstore_RentalStore_war_1.0-SNAPSHOT/ClassificationEJB");
            System.out.println("+++++++++  string " + string);
            System.out.println("voy a buscarlo en el find all en el ic");
            List<User> list = new ArrayList<>();

            list = userServices2.findAll();
            System.out.println("||||||||||||||||| trajo la lista");
            for (User u : list) {
                System.out.println("||||||||||||||||| user " + u.getUsername());
            }
            System.out.println("||||||||||||||||| ahora voy con elk findname");
             Optional<User> optional = userServices2.findByUsername(string);
            if (!optional.isPresent()) {
                System.out.println("|||||||||||||||||No lo encuentra");
            } else {
                System.out.println("||||||||||||||||| voy a convertirlo");
                user = optional.get();
                System.out.println("||||||||||||||||| convertido");
                System.out.println("||||||||||  user name "+user.getName());
            }
            System.out.println("----voy a colocar el user logeado");
            //Coloco el user logeado
            if ((User) JmoordbContext.get("jmoordb_user") == null) {
               
            } else {
     user = (User) JmoordbContext.get("jmoordb_user");
            }
          
        } catch (Exception e) {
            System.out.println(">>>>>>>>-ErrprgetAsObject/(-----retornara el user");
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        System.out.println(">>>>>>>>------retornara el user");
        return user;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, User t) {

        try {
        
            if (t == null) {

                return "";
            }

            if (t.getUsername() != null) {
           
                return t.getUsername();
            }
        } catch (Exception e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
 
        return "";
    }

}
