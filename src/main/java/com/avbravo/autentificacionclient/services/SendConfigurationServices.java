/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.services;

import com.avbravo.autentificacionclient.entity.Profile;
import com.avbravo.autentificacionclient.entity.SendConfiguration;
import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.autentificacionclient.producer.AuthentificationProducer;
import com.avbravo.autentificacionclient.producer.MicroservicesProducer;
import com.avbravo.jmoordb.util.JmoordbDocument;
import com.avbravo.jmoordb.util.JmoordbUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.ws.rs.QueryParam;
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
@LocalBean
public class SendConfigurationServices implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // <editor-fold defaultstate="collapsed" desc="field()">
    String directoryLogger = JmoordbUtil.isLinux() ? JmoordbUtil.userHome() + JmoordbUtil.fileSeparator() + "autentificacionclient" + JmoordbUtil.fileSeparator() + "logs" + JmoordbUtil.fileSeparator() + "logger.json" : "C:\\autentificacionclient\\logs\\logger.json";
    private static final String PASS = "pass";
    private static final String FAIL = "fail";
    private static final String SUCCESS_RESULT = "<result>success</result>";
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

// <editor-fold defaultstate="collapsed" desc="List<SendConfiguration> findAll()">
    public List<SendConfiguration> findAll() {
        List<SendConfiguration> sendConfigurationList = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget target = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/sendConfiguration/findall");

            GenericType<List<SendConfiguration>> data = new GenericType<List<SendConfiguration>>() {
            };

            sendConfigurationList = target.request(MediaType.APPLICATION_JSON).get(data);

        } catch (Exception e) {
        
           exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
        }
        return sendConfigurationList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean add(SendConfiguration sendConfiguration)">
    public Boolean add(SendConfiguration sendConfiguration) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/sendConfiguration/add");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(sendConfiguration, MediaType.APPLICATION_JSON));

          
            if (response.getStatus() == 400) {
                 exception = new Exception(response.readEntity(String.class));
                return false;
            }
            sendConfiguration.setIdsendconfiguration(Integer.parseInt(response.readEntity(String.class)));
            return true;
        } catch (Exception e) {
             
             
             exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
        }
        return false;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Boolean update(SendConfiguration sendConfiguration)">
    public Boolean update(SendConfiguration sendConfiguration) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/sendConfiguration/update");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.entity(sendConfiguration, MediaType.APPLICATION_JSON));

            System.out.println(response.getStatus());
            if (response.getStatus() == 400) {
                 exception = new Exception(response.readEntity(String.class));
                return false;
            }
            System.out.println(response.readEntity(String.class
            ));
            return true;
        } catch (Exception e) {
             
             
             exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
        }
        return false;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Boolean delete(SendConfiguration sendConfiguration)">

    public Boolean delete(SendConfiguration sendConfiguration) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/sendConfiguration/delete");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(sendConfiguration, MediaType.APPLICATION_JSON));

            System.out.println(response.getStatus());
            if (response.getStatus() == 400) {
                 exception = new Exception(response.readEntity(String.class));
                return false;
            }
            System.out.println(response.readEntity(String.class
            ));
            return true;
        } catch (Exception e) {
             
             
             exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
        }
        return false;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Optional<SendConfiguration> findBySendConfiguration(Integer idsendConfiguration) ">
    /**
     * consulta por codigo_pedido impresa
     *
     * @param codigo_
     * @return
     */
    public Optional<SendConfiguration> findByIdsendConfiguration(Integer idsendConfiguration) {
        SendConfiguration sendConfiguration = new SendConfiguration();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            sendConfiguration = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/sendConfiguration/search/")
                    .path("/{idsendConfiguration}")
                    .resolveTemplate("idsendConfiguration", idsendConfiguration)
                    .request(MediaType.APPLICATION_JSON)
                    .get(SendConfiguration.class
                    );
            if(sendConfiguration == null || sendConfiguration.getIdsendconfiguration() == null || sendConfiguration.getModule()== null){
               return Optional.empty();  
            }
            return Optional.of(sendConfiguration);
            //String result = FAIL;
        } catch (Exception e) {
             
             
            System.out.println("findByIdapplicativ() " + e.getLocalizedMessage());
        }
        return Optional.empty();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<SendConfiguration> complete( String query)">
    public List<SendConfiguration> complete(String query) {
        List<SendConfiguration> suggestions = new ArrayList<>();
        try {

            if (query == null || query.isEmpty()) {
                query = "{{complete}}";
            }
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/sendConfiguration/autocomplete/")
                    .path("/{query}")
                    .resolveTemplate("query", query)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<SendConfiguration>>() {
                    });

        } catch (Exception e) {
             
             
            System.out.println("complete() " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog("complete()", e.getLocalizedMessage());
        }

        return suggestions;
    }
    // </editor-fold>
    
     
       
// <editor-fold defaultstate="collapsed" desc=" List<SendConfiguration> jsonQuery(@QueryParam("query") String query , @QueryParam("sort") String sort, @QueryParam("pagenumber") Integer pageNumber, @QueryParam("rowforpage") Integer rowForPage )">

  public  List<SendConfiguration> jsonQuery( String query ,  String sort,
     Integer pageNumber,  Integer rowForPage ){
        List<SendConfiguration> suggestions = new ArrayList<>();
        try {
 

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/sendConfiguration/jsonquery/")                    
                    .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort",JmoordbDocument.encodeJson(sort))
                    .queryParam("pagenumber", pageNumber)
                    .queryParam("rowforpage", rowForPage)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<SendConfiguration>>() {
                    });

        } catch (Exception e) {
             
                         exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
         
        }

        return suggestions;
    }
    // </editor-fold>   
  
  // <editor-fold defaultstate="collapsed" desc=" List<SendConfiguration> jsonQueryWithoutPagination( @QueryParam("query") String query , @QueryParam("sort") String sort  ){">
   
   public List<SendConfiguration> jsonQueryWithoutPagination( String query ,  String sort  ){
        List<SendConfiguration> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/sendConfiguration/jsonquerywithoutpagination/")                    
                 .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort",JmoordbDocument.encodeJson(sort))
                  
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<SendConfiguration>>() {
                    });
        } catch (Exception e) {
             
                    exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
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
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/sendConfiguration/countjsonquery")
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
             
                  exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
        }

        return total;
    }
    // </editor-fold>

    
   // <editor-fold defaultstate="collapsed" desc="List<Profile> findbyApplicativeModuleEventActiveDepartament(Integer idapplicative, String module, String event, Integer iddepartamentLogged, Boolean... active)" >
    /**
     * Busca los usuarios que sean autoridad (isauthority = true o que no sean
     * autoridad isauthority = false) Recuerde que es una lista embebida de
     * muchos roles
     *
     */
    /**
     * 
     * @param idapplicative
     * @param module
     * @param event
     * @param iddepartamentLoged : Cuando el profile tiene 0 en el campo iddepartamento se reemplaza con el iddepartamentlogged
     * @param active
     * @return 
     */
    public List<Profile> findbyApplicativeModuleEventActiveDepartament(Integer idapplicative, String module, String event, Integer iddepartamentLogged, Boolean... active) {
        List<Profile> suggestions = new ArrayList<>();
        try {
            Boolean activeValue = true;
            if (active.length != 0) {
                activeValue = active[0];

            }
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/sendconfiguration/findbyapplicativemoduleeventactive/")
                    .queryParam("idapplicative", idapplicative)
                    .queryParam("module", module)
                    .queryParam("event", event)
                    .queryParam("active", activeValue)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Profile>>() {
                    });

            
            if(suggestions != null && !suggestions.isEmpty()){
                /**
                 * Reemplazar el iddepartament 0 por el iddepartamentLogged que es el envio desde el controller actual
                 */
                Integer count=0;
                System.out.println("++++++++++++++++++++++++++ VERIFICANDO EL IDDEPARTAMEMTO.....");
                for(Profile p:suggestions){
                    
                    if(p.getIddepartament().equals(0)){
                        System.out.println("---- cambiando "+p.getIddepartament() +   " por "+iddepartamentLogged);
                        suggestions.get(count).setIddepartament(iddepartamentLogged);
                    }
                    count++;
                }
            }
        } catch (Exception e) {
             
                   exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
        }

        return suggestions;
    }
    // </editor-fold>
}

