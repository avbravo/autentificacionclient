/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.system.limpieza.services;

import com.avbravo.autentificacionclient.producer.AuthentificationProducer;
import com.avbravo.autentificacionclient.producer.MicroservicesProducer;
import com.avbravo.autentificacionclient.services.LoggerServices;
import com.avbravo.jmoordb.util.JmoordbDocument;
import com.avbravo.jmoordb.util.JmoordbUtil;
import com.avbravo.system.limpieza.entity.Area;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
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
@LocalBean
public class AreaServices implements Serializable {

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

// <editor-fold defaultstate="collapsed" desc="List<Area> findAll()">
    public List<Area> findAll() {
        List<Area> areaList = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget target = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/area/findall");

            GenericType<List<Area>> data = new GenericType<List<Area>>() {
            };

            areaList = target.request(MediaType.APPLICATION_JSON).get(data);

        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             
             
        }
        return areaList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean add(Area area)">
    public Boolean add(Area area) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/area/add");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(area, MediaType.APPLICATION_JSON));

             
            if (response.getStatus() == 400) {
                 exception = new Exception(response.readEntity(String.class));
                return false;
            }
              area.setIdarea(Integer.parseInt(response.readEntity(String.class)));
            
          
            return true;
        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             
             
        }
        return false;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Boolean update(Area area)">
    public Boolean update(Area area) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/area/update");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.entity(area, MediaType.APPLICATION_JSON));

             
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
// <editor-fold defaultstate="collapsed" desc="Boolean delete(Area area)">

    public Boolean delete(Area area) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/area/delete");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(area, MediaType.APPLICATION_JSON));

             
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

    // <editor-fold defaultstate="collapsed" desc="Optional<Area> findByIdarea(Integer idarea)">
    /**
     * consulta por codigo_pedido impresa
     *
     * @param codigo_
     * @return
     */
    public Optional<Area> findByIdarea(Integer idarea) {
        Area area = new Area();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            area = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/area/search/")
                    .path("/{idarea}")
                    .resolveTemplate("idarea", idarea)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Area.class
                    );
            if(area == null || area.getIdarea() == null || area.getArea() == null || area.getArea().equals("")){
               return Optional.empty();  
            }
            return Optional.of(area);
            //String result = FAIL;
        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             
          
        }
        return Optional.empty();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<Area> complete( String query)">
    public List<Area> complete(String query) {
        List<Area> suggestions = new ArrayList<>();
        try {

            if (query == null || query.isEmpty()) {
                query = "{{complete}}";
            }
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/area/autocomplete/")
                    .path("/{query}")
                    .resolveTemplate("query", query)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Area>>() {
                    });

        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             
             
             
        }

        return suggestions;
    }
    // </editor-fold>
    
     
       
// <editor-fold defaultstate="collapsed" desc=" List<Area> jsonQuery(@QueryParam("query") String query , @QueryParam("sort") String sort, @QueryParam("pagenumber") Integer pageNumber, @QueryParam("rowforpage") Integer rowForPage )">

  public  List<Area> jsonQuery( String query ,  String sort,
     Integer pageNumber,  Integer rowForPage ){
        List<Area> suggestions = new ArrayList<>();
        try {
 

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/area/jsonquery/")                    
                    .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort",JmoordbDocument.encodeJson(sort))
                    .queryParam("pagenumber", pageNumber)
                    .queryParam("rowforpage", rowForPage)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Area>>() {
                    });

        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             

             
        }

        return suggestions;
    }
    // </editor-fold>   
  
  // <editor-fold defaultstate="collapsed" desc=" List<Area> jsonQueryWithoutPagination( @QueryParam("query") String query , @QueryParam("sort") String sort  ){">
   
   public List<Area> jsonQueryWithoutPagination( String query ,  String sort  ){
        List<Area> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/area/jsonquerywithoutpagination/")                    
                 .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort",JmoordbDocument.encodeJson(sort))
                  
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Area>>() {
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
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/area/countjsonquery")
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
