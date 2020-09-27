/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.services;

import com.avbravo.asistenciaclient.entity.Boletas;
import com.avbravo.autentificacionclient.entity.Otp;
import com.avbravo.autentificacionclient.producer.AuthentificationProducer;
import com.avbravo.autentificacionclient.producer.MicroservicesProducer;
import com.avbravo.jmoordb.util.JmoordbDateUtil;
import com.avbravo.jmoordb.util.JmoordbUtil;
import com.mongodb.client.model.Filters;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author avbravo
 */
@Stateless
public class OtpServices implements Serializable {

    String directoryLogger = JmoordbUtil.isLinux() ? JmoordbUtil.userHome() + JmoordbUtil.fileSeparator() + "autentificacionclient" + JmoordbUtil.fileSeparator() + "logs" + JmoordbUtil.fileSeparator() + "logger.json" : "C:\\autentificacionclient\\logs\\logger.json";
    private static final String PASS = "pass";
    private static final String FAIL = "fail";
    private static final String SUCCESS_RESULT = "<result>success</result>";
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

// <editor-fold defaultstate="collapsed" desc="List<Otp> findAll()">
    public List<Otp> findAll() {
        List<Otp> otpList = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget target = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/otp/findall");

            GenericType<List<Otp>> data = new GenericType<List<Otp>>() {
            };

            otpList = target.request(MediaType.APPLICATION_JSON).get(data);

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("findAll()" + e.getLocalizedMessage());
        }
        return otpList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean add(Otp otp)">
    public Boolean add(Otp otp) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/otp/add");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(otp, MediaType.APPLICATION_JSON));

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

// <editor-fold defaultstate="collapsed" desc="Boolean update(Otp otp)">
    public Boolean update(Otp otp) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/otp/update");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.entity(otp, MediaType.APPLICATION_JSON));

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
// <editor-fold defaultstate="collapsed" desc="Boolean delete (Otp otp)">

    public Boolean delete(Otp otp) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/otp/delete");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(otp, MediaType.APPLICATION_JSON));

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

    // <editor-fold defaultstate="collapsed" desc="Otp findByOtp(Integer idotp) ">
    /**
     * consulta por codigo_pedido impresa
     *
     * @param codigo_
     * @return
     */
    public Optional<Otp> findByIdotp(Integer idotp) {
        Otp otp = new Otp();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            otp = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/otp/search/")
                    .path("/{idotp}")
                    .resolveTemplate("idotp", idotp)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Otp.class
                    );
            return Optional.of(otp);
            //String result = FAIL;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("findByidotp() " + e.getLocalizedMessage());
        }
        return Optional.empty();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<Otp> complete( String query)">
    public List<Otp> complete(String query) {
        List<Otp> suggestions = new ArrayList<>();
        try {

            if (query == null || query.isEmpty()) {
                query = "{{complete}}";
            }
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/otp/autocomplete/")
                    .path("/{query}")
                    .resolveTemplate("query", query)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Otp>>() {
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
    
    
 // <editor-fold defaultstate="collapsed" desc="String showDate(Date date)">
    public String showDate(Date date) {
        String h = "";
        try {
            h = JmoordbDateUtil.dateFormatToString(date, "dd/MM/yyyy");
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.errorMessage("showDate() " + e.getLocalizedMessage());
        }
        return h;
    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String showHour(Date date)">

    public String showHour(Date date) {
        String h = "";
        try {
            h = JmoordbDateUtil.hourFromDateToString(date);
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.errorMessage("showHour() " + e.getLocalizedMessage());
        }
        return h;
    }
// </editor-fold>    
    
    
    
      // <editor-fold defaultstate="collapsed" desc=" @Path("/findbyanyfield")">

   /**
    * 
    * @param otp
    * @param username
    * @return 
    */
    @GET
    @Path("/findbyanyfield")
    @RolesAllowed({"admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Otp> findByOtpAndUsername( Integer otp,  String username)  { 
        List<Otp> list = new ArrayList<>();
        try {
   Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            list = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/otp/findbyotpandusername/")
                    .queryParam("otp", otp)
                    .queryParam("username", username)
                   
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Otp>>() {
                    });
        } catch (Exception e) {
            System.out.println(JmoordbUtil.nameOfMethod() + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
        }

        return list;
    }
// </editor-fold>
      // <editor-fold defaultstate="collapsed" desc=" @Path("/findbyanyfield")">

   /**
    * 
    * @param otp
    * @param username
    * @return 
    */
    @GET
    @Path("/findbyanyfield")
    @RolesAllowed({"admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Otp> findByEmail(  String email)  { 
        List<Otp> list = new ArrayList<>();
        try {
   Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            list = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/otp/findbyemail/")
                    .queryParam("email", email)
                   
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Otp>>() {
                    });
        } catch (Exception e) {
            System.out.println(JmoordbUtil.nameOfMethod() + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
        }

        return list;
    }
// </editor-fold>
    

}
