/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.limpieza.services;
// <editor-fold defaultstate="collapsed" desc="import">

import com.avbravo.asistenciaclient.services.*;
import com.avbravo.asistenciaclient.entity.Boletas;
import com.avbravo.autentificacionclient.entity.Notifications;
import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.autentificacionclient.producer.AuthentificationProducer;
import com.avbravo.autentificacionclient.producer.MicroservicesProducer;
import com.avbravo.autentificacionclient.services.LoggerServices;
import com.avbravo.autentificacionclient.services.UserServices;
import com.avbravo.jmoordb.util.JmoordbDateUtil;
import com.avbravo.jmoordb.util.JmoordbDocument;
import com.avbravo.jmoordb.util.JmoordbUtil;
import java.util.Date;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
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
// </editor-fold>

/**
 *
 * @author avbravo
 */
@Stateless
public class NotificationsLimpiezaServices implements Serializable {
  // <editor-fold defaultstate="collapsed" desc="fields ">
 Boolean found =false;
    Exception exception;
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="@Inject">
    @Inject
    LoggerServices loggerServices;
    @Inject
    MicroservicesProducer microservicesProducer;

    @Inject
    AuthentificationProducer authentificationProducer;
        @Inject
    UserServices userServices;
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" set/get)">
    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

// </editor-fold>

 

    
// <editor-fold defaultstate="collapsed" desc="List<Notifications> findAll()">
    public List<Notifications> findAll() {
        List<Notifications> notificationsList = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget target = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/notificationslimpieza/findall");

            GenericType<List<Notifications>> data = new GenericType<List<Notifications>>() {
            };

            notificationsList = target.request(MediaType.APPLICATION_JSON).get(data);

        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             
             
        }
        return notificationsList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean add(Notifications notifications)">
    public Boolean add(Notifications notifications) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/notificationslimpieza/add");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(notifications, MediaType.APPLICATION_JSON));

             
            if (response.getStatus() == 400) {
                exception = new Exception(response.readEntity(String.class));
                return false;
            }
   notifications.setIdnotifications(Integer.parseInt(response.readEntity(String.class)));
            return true;
        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             
             
        }
        return false;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Boolean update(Notifications notifications)">
    public Boolean update(Notifications notifications) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/notificationslimpieza/update");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.entity(notifications, MediaType.APPLICATION_JSON));

             
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
// <editor-fold defaultstate="collapsed" desc="Boolean delete(Notifications notifications)">

    public Boolean delete(Notifications notifications) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/notificationslimpieza/delete");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(notifications, MediaType.APPLICATION_JSON));

             
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

    // <editor-fold defaultstate="collapsed" desc="Optional<Notifications> findByIdnotifications(Integer idnotifications) ">
    /**
     * consulta por codigo_pedido impresa
     *
     * @param codigo_
     * @return
     */
    public Optional<Notifications> findByIdnotifications(Integer idnotifications) {
        Notifications notifications = new Notifications();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            notifications = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/notificationslimpieza/search/")
                    .path("/{idnotifications}")
                    .resolveTemplate("idnotifications", idnotifications)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Notifications.class
                    );
            if (notifications == null || notifications.getIdnotifications() == null || notifications.getIdapplicative() == null   ) {
                return Optional.empty();
            }
            return Optional.of(notifications);
            //String result = FAIL;
        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             
   
        }
        return Optional.empty();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<Notifications> complete( String query)">
    public List<Notifications> complete(String query) {
        List<Notifications> suggestions = new ArrayList<>();
        try {

            if (query == null || query.isEmpty()) {
                query = "{{complete}}";
            }
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/notificationslimpieza/autocomplete/")
                    .path("/{query}")
                    .resolveTemplate("query", query)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Notifications>>() {
                    });

        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             
             
             
        }

        return suggestions;
    }
    // </editor-fold>

// <editor-fold defaultstate="collapsed" desc=" List<Notifications> jsonQuery(@QueryParam("query") String query , @QueryParam("sort") String sort, @QueryParam("pagenumber") Integer pageNumber, @QueryParam("rowforpage") Integer rowForPage )">
    public List<Notifications> jsonQuery(String query, String sort,
            Integer pageNumber, Integer rowForPage) {
        List<Notifications> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/notificationslimpieza/jsonquery/")
                    .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort", JmoordbDocument.encodeJson(sort))
                    .queryParam("pagenumber", pageNumber)
                    .queryParam("rowforpage", rowForPage)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Notifications>>() {
                    });

        } catch (Exception e) {
               exception =loggerServices.processException(JmoordbUtil.nameOfClass(),JmoordbUtil.nameOfMethod(), e,false);
             
             
             
        }

        return suggestions;
    }
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc=" List<Notifications> jsonQueryWithoutPagination( @QueryParam("query") String query , @QueryParam("sort") String sort  ){">
    public List<Notifications> jsonQueryWithoutPagination(String query, String sort) {
        List<Notifications> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/notificationslimpieza/jsonquerywithoutpagination/")
                    .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort", JmoordbDocument.encodeJson(sort))
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Notifications>>() {
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
     *
     * @param query
     * @return
     */
    public Integer countJsonQuery(String query) {
        Integer total = 0;
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());

            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/notificationslimpieza/countjsonquery")
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

    // <editor-fold defaultstate="collapsed" desc="List<String> generateList(Boletas boletas, Boolean isJefeUnidad, Boolean isAutoridad, Boolean isRecursosHumanos, String msgJefeUnidad,           String msgAutoridad, String msgRecursos) ">
    public List<String> generateList(Boletas boletas, Boolean isJefeUnidad, Boolean isAutoridad, Boolean isRecursosHumanos, String msgJefeUnidad,
            String msgAutoridad, String msgRecursos) {
        List<String> emailList = new ArrayList<>();
        List<User> list = new ArrayList<>();
        try {

            list.add(boletas.getUser());
            //  list.add(boletas.getUser().getUsername());
            if (isJefeUnidad) {
                list.addAll(searchJefeUnidadDelDepartament(boletas, msgJefeUnidad));
            }
            if (isAutoridad) {

                list = sinDuplicados(list, searchAutoridad(msgAutoridad));
            }
            if (isRecursosHumanos) {

                list = sinDuplicados(list, searchRecursosHumanos(msgRecursos));

            }
            //Carga la lista 
            if (!list.isEmpty()) {
                list.forEach(u -> emailList.add(u.getUsername()));
            }
            /**
             * Agrega el usuario de la boleta
             */
             found =false;
             emailList.forEach(u -> {

                if (u.equals(boletas.getUser().getUsername())) {
                    found =true;
                }
            }
            );
             if(!found){
                 emailList.add(boletas.getUser().getUsername());
             }

        } catch (Exception e) {
             
        }
        return emailList;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" searchJefeUnidadDelDepartament">
    public List<User> searchJefeUnidadDelDepartament(Boletas boletas, String mmsgJefeUnidad) {
        List<User> list = new ArrayList<>();
        try {
            List<User> userJefeUnidad = userServices.searchJefeUnidadDelDepartament(boletas.getDepartament().getIddepartament());
            if (userJefeUnidad == null || userJefeUnidad.isEmpty()) {
                JmoordbUtil.warningMessage(mmsgJefeUnidad);
            } else {
                userJefeUnidad.stream().filter(u -> (!boletas.getUser().getUsername().equals(u.getUsername()))).forEachOrdered(u -> {
                    list.add(u);

                });
            }
        } catch (Exception e) {
             
        }
        return list;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="List<User> searchAutoridad(String msgAutoridad) ">

    public List<User> searchAutoridad(String msgAutoridad) {
        List<User> list = new ArrayList<>();
        try {
            List<User> userAutoridadList = userServices.searchAutoridad(Boolean.TRUE);

            if (userAutoridadList == null || userAutoridadList.isEmpty()) {
                JmoordbUtil.warningMessage(msgAutoridad);
            } else {

                userAutoridadList.forEach(u -> {
                    if (!list.contains(u)) {
                        list.add(u);

                    }
                });
            }

        } catch (Exception e) {
             
        }
        return list;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<User> searchRecursosHumanos(String msgRecursos)">
    public List<User> searchRecursosHumanos(String msgRecursos) {
        List<User> list = new ArrayList<>();
        try {
            List<User> userRecursosHumanosList = userServices.searchRecursosHumanos(Boolean.TRUE);

            if (userRecursosHumanosList == null || userRecursosHumanosList.isEmpty()) {
                JmoordbUtil.warningMessage(msgRecursos);
            } else {

                userRecursosHumanosList.forEach(u -> {
                    if (!list.contains(u)) {

                        list.add(u);
                    }
                }
                );

            }

        } catch (Exception e) {
             
        }
        return list;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<User> sinDuplicados(List<User> listSource, List<User> listToAdd)">
    /**
     * Agrega a la lista el elemento si no esta registrado
     *
     * @param listSource
     * @param listToAdd
     * @return
     */
    public List<User> sinDuplicados(List<User> listSource, List<User> listToAdd) {
        List<User> list = new ArrayList<>();
        try {
            listToAdd.forEach(u -> {

                if (!listSource.contains(u)) {
                    listSource.add(u);

                }
            }
            );

        } catch (Exception e) {
             
        }
        return listSource;
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
    }// </editor-fold>
}
