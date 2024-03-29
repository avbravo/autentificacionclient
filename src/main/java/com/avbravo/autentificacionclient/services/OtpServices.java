/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.services;

import com.avbravo.autentificacionclient.entity.Otp;
import com.avbravo.autentificacionclient.producer.AuthentificationProducer;
import com.avbravo.autentificacionclient.producer.MicroservicesProducer;
import com.avbravo.jmoordb.util.JmoordbDateUtil;
import com.avbravo.jmoordb.util.JmoordbDocument;
import com.avbravo.jmoordb.util.JmoordbUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Produces;
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
public class OtpServices implements Serializable {
// <editor-fold defaultstate="collapsed" desc="fields ">

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
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             
             
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

             
            if (response.getStatus() == 400) {
                 exception = new Exception(response.readEntity(String.class));
                return false;
            }
             
            otp.setIdotp(Integer.parseInt(response.readEntity(String.class)));
            return true;
        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             
             
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

             
            if (response.getStatus() == 400) {
                 exception = new Exception(response.readEntity(String.class));
                return false;
            }
             
      
            return true;
        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             
             
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

             
            if (response.getStatus() == 400) {
                 exception = new Exception(response.readEntity(String.class));
                return false;
            }
             
        
            return true;
        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             
             
        }
        return false;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Optional<Otp>  findByOtp(Integer idotp) ">
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
            if(otp == null || otp.getIdotp() == null || otp.getOtp() == null ){
                     return Optional.empty();
            }
            return Optional.of(otp);
            //String result = FAIL;
        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             
        
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
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             
             
             
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
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
          
        }
        return h;
    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String showHour(Date date)">

    public String showHour(Date date) {
        String h = "";
        try {
            h = JmoordbDateUtil.hourFromDateToString(date);
        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
     
        }
        return h;
    }
// </editor-fold>    
    
    
    

       // <editor-fold defaultstate="collapsed" desc="List<Otp> findByOtpAndIduser( Integer otp,  Integer iduser)" >
/**
 * Busca el opt por otp y iduser
 * @param otp
 * @param iduser
 * @return 
 */

    @Produces(MediaType.APPLICATION_JSON)
    public List<Otp> findByOtpAndIduser( Integer otp,  Integer iduser)  { 
        List<Otp> list = new ArrayList<>();
        try {
   Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            list = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/otp/findbyotpandiduser/")
                    .queryParam("otp", otp)
                    .queryParam("iduser", iduser)
                   
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Otp>>() {
                    });
        } catch (Exception e) {
             
             
        }

        return list;
    }
// </editor-fold>

    
       
       
// <editor-fold defaultstate="collapsed" desc=" List<Otp> jsonQuery(@QueryParam("query") String query , @QueryParam("sort") String sort, @QueryParam("pagenumber") Integer pageNumber, @QueryParam("rowforpage") Integer rowForPage )">

  public  List<Otp> jsonQuery( String query ,  String sort,
     Integer pageNumber,  Integer rowForPage ){
        List<Otp> suggestions = new ArrayList<>();
        try {
 

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/otp/jsonquery/")                    
                    .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort",JmoordbDocument.encodeJson(sort))
                    .queryParam("pagenumber", pageNumber)
                    .queryParam("rowforpage", rowForPage)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Otp>>() {
                    });

        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             

             
        }

        return suggestions;
    }
    // </editor-fold>   
  
  // <editor-fold defaultstate="collapsed" desc=" List<Otp> jsonQueryWithoutPagination( @QueryParam("query") String query , @QueryParam("sort") String sort  ){">
   
   public List<Otp> jsonQueryWithoutPagination( String query ,  String sort  ){
        List<Otp> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/otp/jsonquerywithoutpagination/")                    
                 .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort",JmoordbDocument.encodeJson(sort))
                  
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Otp>>() {
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
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/otp/countjsonquery")
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
}
