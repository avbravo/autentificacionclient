/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.asistenciaclient.services;

import com.avbravo.asistenciaclient.entity.Boletas;
import com.avbravo.autentificacionclient.producer.AuthentificationProducer;
import com.avbravo.autentificacionclient.producer.MicroservicesProducer;
import com.avbravo.jmoordb.util.JmoordbDateUtil;
import com.avbravo.jmoordb.util.JmoordbUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

/**
 *
 * @author avbravo
 */
@Stateless
public class BoletasServices implements Serializable {

    String directoryLogger = JmoordbUtil.isLinux() ? JmoordbUtil.userHome() + JmoordbUtil.fileSeparator() + "autentificacionclient" + JmoordbUtil.fileSeparator() + "logs" + JmoordbUtil.fileSeparator() + "logger.json" : "C:\\autentificacionclient\\logs\\logger.json";
    private static final String PASS = "pass";
    private static final String FAIL = "fail";
    private static final String SUCCESS_RESULT = "<result>success</result>";

    @Inject
    MicroservicesProducer microservicesProducer;

    @Inject
    AuthentificationProducer authentificationProducer;

// <editor-fold defaultstate="collapsed" desc="List<Boletas> findAll()">
    public List<Boletas> findAll() {
        List<Boletas> boletasList = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget target = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/findall");

            GenericType<List<Boletas>> data = new GenericType<List<Boletas>>() {
            };

            boletasList = target.request(MediaType.APPLICATION_JSON).get(data);

        } catch (Exception e) {
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("findAll()" + e.getLocalizedMessage());
        }
        return boletasList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean add(Boletas boletas)">
    public Boolean add(Boletas boletas) {
        try {
            System.out.println(">>>> Client llego a add");
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/add");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(boletas, MediaType.APPLICATION_JSON));

            System.out.println(response.getStatus());
            if (response.getStatus() == 400) {
                return false;
            }
            System.out.println(response.readEntity(String.class
            ));
            return true;
        } catch (Exception e) {
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("errort" + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Boolean update(Boletas boletas)">
    public Boolean update(Boletas boletas) {
        try {
            System.out.println(">>>> Cliente invocando updatre"+boletas.getIdboleta());
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/update");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.entity(boletas, MediaType.APPLICATION_JSON));

            if (response.getStatus() == 400 || response.getStatus() == 405) {
                return false;
            }
            System.out.println("Response " + response.readEntity(String.class));
            return true;
        } catch (Exception e) {
            System.out.println("update" + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);

        }
        return false;
    }
// </editor-fold>
//// <editor-fold defaultstate="collapsed" desc="Boolean delete(Boletas boletas)">
//
//    public Boolean delete(Boletas boletas) {
//        try {
//            Client client = ClientBuilder.newClient();
//            client.register(authentificationProducer.httpAuthenticationFeature());
//            WebTarget webTarget
//                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/delete");
//
//            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
//            Response response = invocationBuilder.post(Entity.entity(boletas, MediaType.APPLICATION_JSON));
//
//            System.out.println(response.getStatus());
//            if (response.getStatus() == 400) {
//                return false;
//            }
//            System.out.println(response.readEntity(String.class
//            ));
//            return true;
//        } catch (Exception e) {
//            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
//            System.out.println("errort" + e.getLocalizedMessage());
//        }
//        return false;
//    }
//// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Boolean delete(Boletas boletas)">

    public Boolean delete(Boletas boletas) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
//            WebTarget webTarget  = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/delete");

            String callResult = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/delete")
                    .path("/{idboleta}")
                    .resolveTemplate("idboleta", boletas.getIdboleta())
                    .request(MediaType.APPLICATION_XML)
                    .delete(String.class);

            System.out.println("*** Call result = " + callResult);
            if (callResult.equals("<result>success</result>")) {
                return true;
            }

        } catch (Exception e) {
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("errort" + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boletas findByBoleta(Integer idboleta) ">
    /**
     * consulta por codigo_pedido impresa
     *
     * @param codigo_
     * @return
     */
    public Optional<Boletas> findByIdboleta(Integer idboleta) {
        Boletas boletas = new Boletas();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            boletas = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/search/")
                    .path("/{idboleta}")
                    .resolveTemplate("idboleta", idboleta)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Boletas.class
                    );
            return Optional.of(boletas);
            //String result = FAIL;
        } catch (Exception e) {
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("findByidboletas() " + e.getLocalizedMessage());
        }
        return Optional.empty();
    }

    // </editor-fold>
  

    // <editor-fold defaultstate="collapsed" desc="List<Boletas> complete( String query)">
    public List<Boletas> complete(String query) {
        List<Boletas> suggestions = new ArrayList<>();
        try {

            if (query == null || query.isEmpty()) {
                query = "{{complete}}";
            }
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/autocomplete/")
                    .path("/{query}")
                    .resolveTemplate("query", query)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Boletas>>() {
                    });

        } catch (Exception e) {
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("complete() " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog("complete()", e.getLocalizedMessage());
        }

        return suggestions;
    }
    // </editor-fold>

     // <editor-fold defaultstate="collapsed" desc="List<Boletas> findByUsername(String username)">
    public List<Boletas> findByUsername(String username) {
        List<Boletas> suggestions = new ArrayList<>();
        try {

          
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/filterbyusername/")
                    .path("/{username}")
                    .resolveTemplate("username", username)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Boletas>>() {
                    });

        } catch (Exception e) {
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("findByUsername) " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog("findByUsername)", e.getLocalizedMessage());
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
            JmoordbUtil.errorMessage("showHour() " + e.getLocalizedMessage());
        }
        return h;
    }// </editor-fold>

}
