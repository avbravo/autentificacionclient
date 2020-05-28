/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.services;

import com.avbravo.autentificacionclient.entity.Institution;
import com.avbravo.autentificacionclient.producer.AuthentificationProducer;
import com.avbravo.autentificacionclient.producer.MicroservicesProducer;
import com.avbravo.jmoordbutils.JsfUtil;
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

/**
 *
 * @author avbravo
 */
@Stateless
public class InstitutionServices implements Serializable {

    String directoryLogger = JsfUtil.isLinux() ? JsfUtil.userHome() + JsfUtil.fileSeparator() + "autentiticacionclient" + JsfUtil.fileSeparator() + "logs" + JsfUtil.fileSeparator() + "logger.json" : "C:\\autentiticacionclient\\logs\\logger.json";
    private static final String PASS = "pass";
    private static final String FAIL = "fail";
    private static final String SUCCESS_RESULT = "<result>success</result>";

    @Inject
    MicroservicesProducer microservicesProducer;

    @Inject
    AuthentificationProducer authentificationProducer;

// <editor-fold defaultstate="collapsed" desc="List<Institution> findAll()">
    public List<Institution> findAll() {
        List<Institution> institutionList = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget target = client.target(microservicesProducer.microservicesHost() + "/autentiticacion/resources/institution/findall");

            GenericType<List<Institution>> data = new GenericType<List<Institution>>() {
            };

            institutionList = target.request(MediaType.APPLICATION_JSON).get(data);

        } catch (Exception e) {
            JsfUtil.appendTextToLogErrorFile(this.directoryLogger, JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("findAll()" + e.getLocalizedMessage());
        }
        return institutionList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean add(Institution institution)">
    public Boolean add(Institution institution) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentiticacion/resources/institution/add");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(institution, MediaType.APPLICATION_JSON));

            System.out.println(response.getStatus());
            if (response.getStatus() == 400) {
                return false;
            }
            System.out.println(response.readEntity(String.class
            ));
            return true;
        } catch (Exception e) {
            JsfUtil.appendTextToLogErrorFile(this.directoryLogger, JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("errort" + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Boolean update(Institution institution)">
    public Boolean update(Institution institution) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentiticacion/resources/institution/update");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(institution, MediaType.APPLICATION_JSON));

            System.out.println(response.getStatus());
            if (response.getStatus() == 400) {
                return false;
            }
            System.out.println(response.readEntity(String.class
            ));
            return true;
        } catch (Exception e) {
            JsfUtil.appendTextToLogErrorFile(this.directoryLogger, JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("errort" + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Boolean update(Institution institution)">

    public Boolean delete(Institution institution) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentiticacion/resources/institution/delete");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(institution, MediaType.APPLICATION_JSON));

            System.out.println(response.getStatus());
            if (response.getStatus() == 400) {
                return false;
            }
            System.out.println(response.readEntity(String.class
            ));
            return true;
        } catch (Exception e) {
            JsfUtil.appendTextToLogErrorFile(this.directoryLogger, JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("errort" + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Institution findByInstitution(Integeridinstitution) ">
    /**
     * consulta por codigo_pedido impresa
     *
     * @param codigo_
     * @return
     */
    public Optional<Institution> findByIdinstitution(Integer idinstitution) {
        Institution institution = new Institution();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            institution = client
                    .target(microservicesProducer.microservicesHost() + "/autentiticacion/resources/institution/search/")
                    .path("/{idinstitution}")
                    .resolveTemplate("idinstitution", idinstitution)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Institution.class
                    );
            return Optional.of(institution);
            //String result = FAIL;
        } catch (Exception e) {
            JsfUtil.appendTextToLogErrorFile(this.directoryLogger, JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("findByidinstitution() " + e.getLocalizedMessage());
        }
        return Optional.empty();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<Institution> complete( String query)">
    public List<Institution> complete(String query) {
        List<Institution> suggestions = new ArrayList<>();
        try {

            if (query == null || query.isEmpty()) {
                query = "{{complete}}";
            }
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentiticacion/resources/institution/autocomplete/")
                    .path("/{query}")
                    .resolveTemplate("query", query)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Institution>>() {
                    });

        } catch (Exception e) {
            JsfUtil.appendTextToLogErrorFile(this.directoryLogger, JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("complete() " + e.getLocalizedMessage());
            JsfUtil.errorDialog("complete()", e.getLocalizedMessage());
        }

        return suggestions;
    }
    // </editor-fold>

}
