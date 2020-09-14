/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.services;

import com.avbravo.autentificacionclient.entity.EmailConfiguration;
import com.avbravo.autentificacionclient.producer.AuthentificationProducer;
import com.avbravo.autentificacionclient.producer.MicroservicesProducer;
import com.avbravo.jmoordb.email.JmoordbEmailSender;
import com.avbravo.jmoordb.util.JmoordbDateUtil;
import com.avbravo.jmoordb.util.JmoordbUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author avbravo
 */
@Stateless
public class EmailConfigurationServices implements Serializable {
// <editor-fold defaultstate="collapsed" desc=" field()">

    String directoryLogger = JmoordbUtil.isLinux() ? JmoordbUtil.userHome() + JmoordbUtil.fileSeparator() + "autentificacionclient" + JmoordbUtil.fileSeparator() + "logs" + JmoordbUtil.fileSeparator() + "logger.json" : "C:\\autentificacionclient\\logs\\logger.json";
    private static final String PASS = "pass";
    private static final String FAIL = "fail";
    private static final String SUCCESS_RESULT = "<result>success</result>";
    
    JmoordbEmailSender jmoordbEmailSender= new  JmoordbEmailSender  ();
    Exception exception;
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="inject()">
    @Inject
    MicroservicesProducer microservicesProducer;

    @Inject
    AuthentificationProducer authentificationProducer;
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="List<EmailConfiguration> findAll()">
    public List<EmailConfiguration> findAll() {
        List<EmailConfiguration> emailConfigurationList = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget target = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/emailconfiguration/findall");

            GenericType<List<EmailConfiguration>> data = new GenericType<List<EmailConfiguration>>() {
            };

            emailConfigurationList = target.request(MediaType.APPLICATION_JSON).get(data);

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("findAll()" + e.getLocalizedMessage());
        }
        return emailConfigurationList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean add(EmailConfiguration emailConfiguration)">
    public Boolean add(EmailConfiguration emailConfiguration) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/emailconfiguration/add");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(emailConfiguration, MediaType.APPLICATION_JSON));

            if (response.getStatus() == 400) {
                exception = new Exception(response.readEntity(String.class));
                // System.out.println("En el Services:" + response.readEntity(String.class));
                //JmoordbUtil.e
                return false;
            }

            return true;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("errort" + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Boolean update(EmailConfiguration emailConfiguration)">
    public Boolean update(EmailConfiguration emailConfiguration) {
        try {
   
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/emailconfiguration/update");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.entity(emailConfiguration, MediaType.APPLICATION_JSON));

            if (response.getStatus() == 400 || response.getStatus() == 405) {

                exception = new Exception(response.readEntity(String.class));
                return false;
            }
            //  System.out.println("Response " + response.readEntity(String.class));
            return true;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            System.out.println("update" + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);

        }
        return false;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Boolean delete(EmailConfiguration emailConfiguration)">

    public Boolean delete(EmailConfiguration emailConfiguration) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
//            WebTarget webTarget  = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/emailconfiguration/delete");

            String callResult = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/emailconfiguration/delete")
                    .path("/{idboleta}")
                    .resolveTemplate("idboleta", emailConfiguration.getIdEmailConfiguration())
                    .request(MediaType.APPLICATION_XML)
                    .delete(String.class);

            //  System.out.println("*** Call result = " + callResult);
            if (callResult.equals("<result>success</result>")) {
                 // exception = new Exception(response.readEntity(String.class));
                return true;
            }

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("errort" + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Optional<EmailConfiguration> findByIdEmailConfiguration(Integer idemailconfiguration) ">
    /**
     * 
     * @param codigo_
     * @return
     */
    public Optional<EmailConfiguration> findByIdEmailConfiguration(Integer idemailconfiguration) {
        EmailConfiguration emailConfiguration = new EmailConfiguration();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            emailConfiguration = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/emailconfiguration/searchidemailconfiguration/")
                    .path("/{idemailconfiguration}")
                    .resolveTemplate("idemailconfiguration", idemailconfiguration)
                    .request(MediaType.APPLICATION_JSON)
                    .get(EmailConfiguration.class
                    );
            return Optional.of(emailConfiguration);
            //String result = FAIL;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod()+ e.getLocalizedMessage());
        }
        return Optional.empty();
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Optional<EmailConfiguration> findByEmail(String email)  ">
    /**
     * 
     * @param codigo_
     * @return
     */
    public Optional<EmailConfiguration> findByEmail(String email) {
        EmailConfiguration emailConfiguration = new EmailConfiguration();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            emailConfiguration = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/emailconfiguration/searchemail/")
                    .path("/{email}")
                    .resolveTemplate("email", email)
                    .request(MediaType.APPLICATION_JSON)
                    .get(EmailConfiguration.class
                    );
            return Optional.of(emailConfiguration);
            //String result = FAIL;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod()+ e.getLocalizedMessage());
        }
        return Optional.empty();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Optional<EmailConfiguration> findByEmail(String email)  ">
    /**
     * 
     * @param codigo_
     * @return
     */
    public Optional<EmailConfiguration> findByIdApplicative(Integer idapplicative) {
        EmailConfiguration emailConfiguration = new EmailConfiguration();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            emailConfiguration = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/emailconfiguration/searchidapplicative/")
                    .path("/{idapplicative}")
                    .resolveTemplate("idapplicative", idapplicative)
                    .request(MediaType.APPLICATION_JSON)
                    .get(EmailConfiguration.class
                    );
            return Optional.of(emailConfiguration);
            //String result = FAIL;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod()+ e.getLocalizedMessage());
        }
        return Optional.empty();
    }

    // </editor-fold>
   

   

    
    
    
     // <editor-fold defaultstate="collapsed" desc="Future<String> sendEmailAsync(String emailreceptor, String titulo, String mensaje, String emailemisor, String passwordemisor)">
    public Future<String> sendEmailAsync(String emailreceptor, String titulo, String mensaje, String emailemisor, String passwordemisor) throws InterruptedException {

        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(new Callable<Object>() {

            @Override
            public Object call() throws Exception {

               jmoordbEmailSender.sendOutlook(emailreceptor, titulo, mensaje, emailemisor, passwordemisor);

                completableFuture.complete("enviado");

                return null;
            }
        });

        return completableFuture;
    }// </editor-fold>
//    // <editor-fold defaultstate="collapsed" desc="Future<String> sendEmailCccBccAsync(String[] to, String[] cc, String[] bcc, String titulo, String mensaje, String emailemisor, String passwordemisor)">
//

    public Future<String> sendEmailCccBccAsync(String[] to, String[] cc, String[] bcc, String titulo, String mensaje, String emailemisor, String passwordemisor) throws InterruptedException {

        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {

               jmoordbEmailSender.sendOutlook(to, cc, bcc, titulo, mensaje, emailemisor, passwordemisor, false);
                completableFuture.complete("enviado");

                return null;
            }
        });

        return completableFuture;
    }// </editor-fold>

    
}
