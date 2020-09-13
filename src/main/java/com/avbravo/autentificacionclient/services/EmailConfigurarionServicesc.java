/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.asistenciaclient.services;

import com.avbravo.autentificacionclient.producer.AuthentificationProducer;
import com.avbravo.autentificacionclient.producer.MicroservicesProducer;
import com.avbravo.jmoordb.email.JmoordbEmailMaster;
import com.avbravo.jmoordb.email.JmoordbManagerEmailOld;
import com.avbravo.jmoordb.util.JmoordbUtil;
import java.io.Serializable;
import java.util.ArrayList;
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
public class JmoordbEmailMasterServices implements Serializable {

String directoryLogger = JmoordbUtil.isLinux() ? JmoordbUtil.userHome() + JmoordbUtil.fileSeparator() + "autentificacionclient" + JmoordbUtil.fileSeparator() + "logs" + JmoordbUtil.fileSeparator() + "logger.json" : "C:\\autentificacionclient\\logs\\logger.json";
    private static final String PASS = "pass";
    private static final String FAIL = "fail";
    private static final String SUCCESS_RESULT = "<result>success</result>";
    JmoordbEmailMaster jmoordbMmanagerEmail = new JmoordbEmailMaster();
    
JmoordbManagerEmailOld  jmoordbManagerEmailOld = new JmoordbManagerEmailOld ();

  Exception exception;

    @Inject
    MicroservicesProducer microservicesProducer;

    @Inject
    AuthentificationProducer authentificationProducer;
    
     // <editor-fold defaultstate="collapsed" desc=" set/get)">
    
    
    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
    
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="List<JmoordbEmailMaster> findAll()">
    public List<JmoordbEmailMaster> findAll() {
        List<JmoordbEmailMaster> jmoordbEmailMasterList = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget target = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/jmoordbemailmaster/findall");

            GenericType<List<JmoordbEmailMaster>> data = new GenericType<List<JmoordbEmailMaster>>() {
            };

            jmoordbEmailMasterList = target.request(MediaType.APPLICATION_JSON).get(data);

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("findAll()" + e.getLocalizedMessage());
        }
        return jmoordbEmailMasterList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean add(JmoordbEmailMaster jmoordbEmailMaster)">
    public Boolean add(JmoordbEmailMaster jmoordbEmailMaster) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/resources/jmoordbemailmaster/add");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(jmoordbEmailMaster, MediaType.APPLICATION_JSON));

            System.out.println(response.getStatus());
            if (response.getStatus() == 400) {
                 exception = new Exception(response.readEntity(String.class));
                return false;
            }
            System.out.println(response.readEntity(String.class
            ));
            return true;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("errort" + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Boolean update(JmoordbEmailMaster jmoordbEmailMaster)">
    public Boolean update(JmoordbEmailMaster jmoordbEmailMaster) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/resources/jmoordbemailmaster/update");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.entity(jmoordbEmailMaster, MediaType.APPLICATION_JSON));

            System.out.println(response.getStatus());
            if (response.getStatus() == 400) {
                 exception = new Exception(response.readEntity(String.class));
                return false;
            }
            System.out.println(response.readEntity(String.class
            ));
            return true;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("errort" + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Boolean delete(JmoordbEmailMaster jmoordbEmailMaster)">

    public Boolean delete(JmoordbEmailMaster jmoordbEmailMaster) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/resources/jmoordbemailmaster/delete");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(jmoordbEmailMaster, MediaType.APPLICATION_JSON));

            System.out.println(response.getStatus());
            if (response.getStatus() == 400) {
                 exception = new Exception(response.readEntity(String.class));
                return false;
            }
            System.out.println(response.readEntity(String.class
            ));
            return true;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("errort" + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Optional<JmoordbEmailMaster> findByEmail(String email)">
    /**
     * consulta por codigo_pedido impresa
     *
     * @param codigo_
     * @return
     */
    public Optional<JmoordbEmailMaster> findByEmail(String email) {
        JmoordbEmailMaster jmoordbEmailMaster = new JmoordbEmailMaster();
        try {
        
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            jmoordbEmailMaster = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/resources/jmoordbemailmaster/search/")
                    .path("/{email}")
                    .resolveTemplate("email", email)
                    .request(MediaType.APPLICATION_JSON)
                    .get(JmoordbEmailMaster.class
                    );
            if(jmoordbEmailMaster == null || jmoordbEmailMaster.getEmail()== null || jmoordbEmailMaster.getEmail()== null || jmoordbEmailMaster.getEmail().isEmpty()){
              
                 return Optional.empty();
            }
      
            return Optional.of(jmoordbEmailMaster);
            //String result = FAIL;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
              System.out.println("findBJmoordbEmailMastername() " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
          
        }

        return Optional.empty();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<JmoordbEmailMaster> complete( String query)">
    public List<JmoordbEmailMaster> complete(String query) {
        List<JmoordbEmailMaster> suggestions = new ArrayList<>();
        try {

            if (query == null || query.isEmpty()) {
                query = "{{complete}}";
            }
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/jmoordbemailmaste/complete/query/")
                    .path("/{nombre}")
                    .resolveTemplate("nombre", query)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<JmoordbEmailMaster>>() {
                    });

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("complete() " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog("complete()", e.getLocalizedMessage());
        }

        return suggestions;
    }
    // </editor-fold>

     // <editor-fold defaultstate="collapsed" desc="Future<String> sendEmailAsync(String emailreceptor, String titulo, String mensaje, String emailemisor, String passwordemisor)">
    public Future<String> sendEmailAsync(String emailreceptor, String titulo, String mensaje, String emailemisor, String passwordemisor) throws InterruptedException {

        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(new Callable<Object>() {

            @Override
            public Object call() throws Exception {

               jmoordbManagerEmailOld.sendOutlook(emailreceptor, titulo, mensaje, emailemisor, passwordemisor);

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

               jmoordbManagerEmailOld.sendOutlook(to, cc, bcc, titulo, mensaje, emailemisor, passwordemisor, false);
                completableFuture.complete("enviado");

                return null;
            }
        });

        return completableFuture;
    }// </editor-fold>

    
    
  
}
