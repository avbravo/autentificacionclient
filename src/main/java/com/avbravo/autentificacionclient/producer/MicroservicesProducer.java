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

/**
 *
 * @author avbravo
 */
@Named
@ApplicationScoped
public class MicroservicesProducer implements Serializable {

    String directoryLogger = JsfUtil.isLinux() ? JsfUtil.userHome() + JsfUtil.fileSeparator() + "autentificacionclient" + JsfUtil.fileSeparator() + "logs" + JsfUtil.fileSeparator() + "logger.json" : "C:\\autentificacionclient\\logs\\logger.json";
    private String url = null;

    public String microservicesHost() {

        try {

            if (url == null) {

                InputStream inputStream = getClass()
                        .getClassLoader().getResourceAsStream("microservices.properties");
                Properties prop = new Properties();

                if (inputStream != null) {

                    prop.load(inputStream);
                    url = prop.getProperty("url");

                    //  System.out.println("el url del microservices es "+url);
                } else {
                    JsfUtil.errorMessage("No se puede cargar el archivo microservices.properties");
                }

            }
        } catch (Exception e) {
            JsfUtil.appendTextToLogErrorFile(this.directoryLogger, JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            JsfUtil.errorMessage("microservicesHost() " + e.getLocalizedMessage());
        }
        return url;
    }

}
