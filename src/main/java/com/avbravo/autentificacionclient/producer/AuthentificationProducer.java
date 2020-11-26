/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.producer;

import com.avbravo.jmoordb.util.JmoordbUtil;
import static com.avbravo.jmoordb.util.JmoordbUtil.isLinux;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 *
 * @author avbravo
 */
@Named
@ApplicationScoped
public class AuthentificationProducer implements Serializable {

    String directoryLogger = isLinux() ? JmoordbUtil.userHome() + JmoordbUtil.fileSeparator() + "autentificacionclient" + JmoordbUtil.fileSeparator() + "logs" + JmoordbUtil.fileSeparator() + "logger.json" : "C:\\auctentifcacionclient\\logs\\logger.json";
    String userAutentification;
    String passwordAutentification;
    // <editor-fold defaultstate="collapsed" desc="Microprofile Config">
    @Inject
    private Config config;
    //otp
    @Inject
    @ConfigProperty(name = "userSecurity", defaultValue = "")
    private Provider<String> userSecurity;
    @Inject
    @ConfigProperty(name = "passwordSecurity", defaultValue = "")
    private Provider<String> passwordSecurity;

    //#--Path Images
    @Inject
    @ConfigProperty(name = "pathBaseLinuxAddUserHome", defaultValue = "true")
    private Provider<Boolean> pathBaseLinuxAddUserHome;

    @Inject
    @ConfigProperty(name = "pathLinux", defaultValue = " ")
    private Provider<String> pathLinux;
    @Inject
    @ConfigProperty(name = "pathWindows", defaultValue = " ")
    private Provider<String> pathWindows;
    @Inject
    @ConfigProperty(name = "urlMicroservices", defaultValue = " ")
    private Provider<String> urlMicroservices;
 // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="init">
    @PostConstruct
    public void init() {
        try {
       
        } catch (Exception e) {
           
        }

    }

   
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="HttpAuthenticationFeature httpAuthenticationFeature()">
    /**
     * Devuelve la autentificacion para ser usada con el Client
     *
     * @return
     */
    public HttpAuthenticationFeature httpAuthenticationFeature() {
        HttpAuthenticationFeature httpAuthenticationFeature = null;
        try {
             userAutentification = JmoordbUtil.desencriptar(userSecurity.get());
            passwordAutentification = JmoordbUtil.desencriptar(passwordSecurity.get());
            httpAuthenticationFeature = HttpAuthenticationFeature.basic(userAutentification,  passwordAutentification);

        } catch (Exception e) {
           JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
 
   JmoordbUtil.errorMessage("HttpAuthenticationFeature () " + e.getLocalizedMessage());
        }

        return httpAuthenticationFeature;
    }

    // </editor-fold>
   

}
