/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.producer;


import com.avbravo.jmoordbutils.JsfUtil;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 *
 * @author avbravo
 */
@Named
@ApplicationScoped
public class AuthentificationProducer implements Serializable {
 String directoryLogger = JsfUtil.isLinux()?JsfUtil.userHome() + JsfUtil.fileSeparator() + "fiscalprinter" + JsfUtil.fileSeparator() + "logs" + JsfUtil.fileSeparator() + "logger.json": "C:\\fiscalprinter\\logs\\logger.json";
    private String user = null;
    private String password = null;

    // <editor-fold defaultstate="collapsed" desc="HttpAuthenticationFeature httpAuthenticationFeature()">
    /**
     * Devuelve la autentificacion para ser usada con el Client
     *
     * @return
     */
    public HttpAuthenticationFeature httpAuthenticationFeature() {
        HttpAuthenticationFeature httpAuthenticationFeature = null;
        try {
            readAuthentificationProperties();
            httpAuthenticationFeature = HttpAuthenticationFeature.basic(user, password);

        } catch (Exception e) {   JsfUtil.appendTextToLogErrorFile(this.directoryLogger, JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            JsfUtil.errorMessage("HttpAuthenticationFeature () " + e.getLocalizedMessage());
        }

        return httpAuthenticationFeature;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean readAuthentificationProperties()">
    /**
     * Lee los archivos de configuracion de los properties
     *
     * @return
     */
    private Boolean readAuthentificationProperties() {

        try {

            if (user == null) {

                InputStream inputStream = getClass()
                        .getClassLoader().getResourceAsStream("authentification.properties");
                Properties prop = new Properties();

                if (inputStream != null) {

                    prop.load(inputStream);
                    if (prop.getProperty("user") == null) {
                        JsfUtil.warningDialog("Advertencia", "Consulte al desarrollador no se encuentra el usuario de autentificacion");
                    } else {
                        user = prop.getProperty("user");
                          user = JsfUtil.desencriptar(user);
                    }
                    if (prop.getProperty("password") == null) {
                        JsfUtil.warningDialog("Advertencia", "Consulte al desarrollador no se encuentra el password del usuario de autentificacion");
                    } else {
                        password = prop.getProperty("password");
                           password = JsfUtil.desencriptar(password);
                    }

                  
                 
                    return true;
                } else {
                    JsfUtil.errorMessage("No se puede cargar el archivo authentification.properties");
                }

            }
        } catch (Exception e) {   JsfUtil.appendTextToLogErrorFile(this.directoryLogger, JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            JsfUtil.errorMessage("readAuthentificationProperties() " + e.getLocalizedMessage());
        }
        return false;
    }
    // </editor-fold>

}
