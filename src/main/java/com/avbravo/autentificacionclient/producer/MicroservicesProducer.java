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
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@ApplicationScoped
public class MicroservicesProducer implements Serializable {

    String directoryLogger = JmoordbUtil.isLinux() ? JmoordbUtil.userHome() + JmoordbUtil.fileSeparator() + "autentificacionclient" + JmoordbUtil.fileSeparator() + "logs" + JmoordbUtil.fileSeparator() + "logger.json" : "C:\\autentificacionclient\\logs\\logger.json";
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
                    JmoordbUtil.errorMessage("No se puede cargar el archivo microservices.properties");
                }

            }
        } catch (Exception e) {
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            JmoordbUtil.errorMessage("microservicesHost() " + e.getLocalizedMessage());
        }
        return url;
    }

}
