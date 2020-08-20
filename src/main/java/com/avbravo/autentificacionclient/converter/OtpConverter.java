/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.Otp;
import com.avbravo.autentificacionclient.entity.Otp;
import com.avbravo.autentificacionclient.services.OtpServices;
import com.avbravo.autentificacionclient.services.OtpServices;
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
@FacesConverter( forClass=Otp.class, managed = true)
public class OtpConverter implements Converter<Otp> {

   @Inject
    OtpServices otpServices;

    @Override
    public Otp getAsObject(FacesContext fc, UIComponent uic, String string) {
          Otp otp = new Otp();
        try {
            if (string  == null || string.isEmpty()) {
            return null;
        }
             Optional<Otp> optional = otpServices.findByIdotp(Integer.parseInt(string));
                if (optional.isPresent()) {
                    otp = optional.get();
                }
        } catch (Exception e) {
               JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return otp;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Otp t) {
     
       try{
            if (t == null) {

                return "";
            }

            if (t.getIdotp()!= null) {

                return t.getIdotp().toString();
            } else {

                //JmoordbUtil.warningDialog("No es valido el id ","");
            }

        } catch (Exception e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return "";
    }

}
