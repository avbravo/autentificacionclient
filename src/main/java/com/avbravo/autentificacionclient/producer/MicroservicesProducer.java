/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.producer;

import com.avbravo.jmoordb.util.JmoordbUtil;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author avbravo
 */
@Named
@ApplicationScoped
public class MicroservicesProducer implements Serializable {

    String directoryLogger = JmoordbUtil.isLinux() ? JmoordbUtil.userHome() + JmoordbUtil.fileSeparator() + "autentificacionclient" + JmoordbUtil.fileSeparator() + "logs" + JmoordbUtil.fileSeparator() + "logger.json" : "C:\\autentificacionclient\\logs\\logger.json";
    private String url = null;
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

    public String microservicesHost() {

        try {

            if (url == null) {
                url = urlMicroservices.get();

            }
        } catch (Exception e) {
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            JmoordbUtil.errorMessage("microservicesHost() " + e.getLocalizedMessage());
        }
        return url;
    }
}
