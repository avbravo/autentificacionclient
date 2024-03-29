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
import com.avbravo.jmoordb.util.JmoordbDocument;
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
public class EmailConfigurationServices implements Serializable {
    
      // <editor-fold defaultstate="collapsed" desc="fields ">
    JmoordbEmailSender jmoordbEmailSender= new  JmoordbEmailSender();
    Exception exception;
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="@Inject">
    @Inject
    LoggerServices loggerServices;
    @Inject
    MicroservicesProducer microservicesProducer;

    @Inject
    AuthentificationProducer authentificationProducer;
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" set/get)">
    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

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
            
                return false;
            }
 emailConfiguration.setIdEmailConfiguration(Integer.parseInt(response.readEntity(String.class)));
            return true;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
             
             
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
 
 
            return true;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
          
             

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
                    .path("/{idemailconfiguration}")
                    .resolveTemplate("idemailconfiguration", emailConfiguration.getIdEmailConfiguration())
                    .request(MediaType.APPLICATION_XML)
                    .delete(String.class);


            if (callResult.equals("Ok")) {
                 // exception = new Exception(response.readEntity(String.class));
                return true;
            }

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
             
             
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
             if(emailConfiguration == null || emailConfiguration.getIdEmailConfiguration() == null || emailConfiguration.getEmail() == null){
              return Optional.empty();
            }
            return Optional.of(emailConfiguration);
            //String result = FAIL;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
      
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
             if(emailConfiguration == null || emailConfiguration.getIdEmailConfiguration() == null){
              return Optional.empty();
            }
            return Optional.of(emailConfiguration);
            //String result = FAIL;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
             
   
        }
        return Optional.empty();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Optional<EmailConfiguration> findByIdApplicative(Integer idapplicative) ">
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
            
            if(emailConfiguration == null || emailConfiguration.getIdEmailConfiguration() == null){
              return Optional.empty();
            }
            return Optional.of(emailConfiguration);
            //String result = FAIL;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
             
        
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

      
    
    
       
// <editor-fold defaultstate="collapsed" desc=" List<Emailconfiguration> jsonQuery(@QueryParam("query") String query , @QueryParam("sort") String sort, @QueryParam("pagenumber") Integer pageNumber, @QueryParam("rowforpage") Integer rowForPage )">

  public  List<EmailConfiguration> jsonQuery( String query ,  String sort,
     Integer pageNumber,  Integer rowForPage ){
        List<EmailConfiguration> suggestions = new ArrayList<>();
        try {
 

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/emailconfiguration/jsonquery/")                    
                    .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort",JmoordbDocument.encodeJson(sort))
                    .queryParam("pagenumber", pageNumber)
                    .queryParam("rowforpage", rowForPage)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<EmailConfiguration>>() {
                    });

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
             

             
        }

        return suggestions;
    }
    // </editor-fold>   
  
  // <editor-fold defaultstate="collapsed" desc=" List<Emailconfiguration> jsonQueryWithoutPagination( @QueryParam("query") String query , @QueryParam("sort") String sort  ){">
   
   public List<EmailConfiguration> jsonQueryWithoutPagination( String query ,  String sort  ){
        List<EmailConfiguration> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/emailconfiguration/jsonquerywithoutpagination/")                    
                 .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort",JmoordbDocument.encodeJson(sort))
                  
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<EmailConfiguration>>() {
                    });
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
             
         
        }

        return suggestions;
    }
    // </editor-fold>   
    
    
   
    // <editor-fold defaultstate="collapsed" desc="Integer countJsonQuery(String query)">

    /**
     * devuelve el contador de documentos en base a un json query
     * @param query
     * @return 
     */

    public Integer countJsonQuery(String query) {
        Integer total = 0;
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());

            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/emailconfiguration/countjsonquery")
                             .queryParam("query", JmoordbDocument.encodeJson(query));

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();
            if (response.getStatus() == 201) {
                total = Integer.parseInt(response.readEntity(String.class));

            }

            if (response.getStatus() == 400) {
                exception = new Exception(response.readEntity(String.class));
                return 0;
            }

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
             
             
             
        }

        return total;
    }
    // </editor-fold>
    
}
