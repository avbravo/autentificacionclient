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
import com.avbravo.jmoordb.util.JmoordbDocument;
import com.avbravo.jmoordb.util.JmoordbUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
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
public class BoletasServices implements Serializable {
// <editor-fold defaultstate="collapsed" desc=" field()">

    String directoryLogger = JmoordbUtil.isLinux() ? JmoordbUtil.userHome() + JmoordbUtil.fileSeparator() + "autentificacionclient" + JmoordbUtil.fileSeparator() + "logs" + JmoordbUtil.fileSeparator() + "logger.json" : "C:\\autentificacionclient\\logs\\logger.json";
    private static final String PASS = "pass";
    private static final String FAIL = "fail";
    private static final String SUCCESS_RESULT = "<result>success</result>";
    Exception exception;
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="inject()">
    @Inject
    MicroservicesProducer microservicesProducer;

    @Inject
    AuthentificationProducer authentificationProducer;
// </editor-fold>

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
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("findAll()" + e.getLocalizedMessage());
        }
        return boletasList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean add(Boletas boletas)">
    public Boolean add(Boletas boletas) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/add");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(boletas, MediaType.APPLICATION_JSON));

            if (response.getStatus() == 400) {
                exception = new Exception(response.readEntity(String.class));
                // System.out.println("En el Services:" + response.readEntity(String.class));
                //JmoordbUtil.e
                return false;
            }
boletas.setIdboleta(Integer.parseInt(response.readEntity(String.class)));
            return true;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("errort" + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean addReturnid(Boletas boletas)">
    /**
     * Retorna el id de la boleta creada, ya que es un autoincrementable
     * @param boletas
     * @return 
     */
    public Integer addReturnid(Boletas boletas) {
        Integer id =0;
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/addreturnid");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(boletas, MediaType.APPLICATION_JSON));

              if (response.getStatus() == 201) {
                id= Integer.parseInt(response.readEntity(String.class));

            }
            
            if (response.getStatus() == 400) {
                exception = new Exception(response.readEntity(String.class));
                return 0;
            }

          
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("errort" + e.getLocalizedMessage());
        }
        return id;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Boolean update(Boletas boletas)">
    public Boolean update(Boletas boletas) {
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/update");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.entity(boletas, MediaType.APPLICATION_JSON));

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

   
    // <editor-fold defaultstate="collapsed" desc="Boletas findByBoleta(Integer idboleta) ">
   
    /**
     * 
     * @param idboleta
     * @return 
     */
    public Optional<Boletas> findByIdboleta(Integer idboleta) {
        Boletas boletas = new Boletas();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            boletas = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/findbyidboleta/")
                    .path("/{idboleta}")
                    .resolveTemplate("idboleta", idboleta)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Boletas.class
                    );
            if(boletas == null || boletas.getIdboleta() == null){
                 return Optional.empty();
            }
            return Optional.of(boletas);
            //String result = FAIL;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println( JmoordbUtil.nameOfMethod() + e.getLocalizedMessage());
        }
        return Optional.empty();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boletas findByBoleta(Integer idboleta) ">
   
    /**
     * 
     * @param idboleta
     * @return 
     */
    public List<Boletas> findByIdDepartament(Integer iddepartament) {
       List<Boletas> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
             suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/findbyiddepartament/")
                    .path("/{iddepartament}")
                    .resolveTemplate("iddepartament", iddepartament)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Boletas>>() {
                    });
            //String result = FAIL;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println( JmoordbUtil.nameOfMethod() + e.getLocalizedMessage());
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
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/findbyusername/")
                    .path("/{username}")
                    .resolveTemplate("username", username)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Boletas>>() {
                    });

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("findByUsername) " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog("findByUsername)", e.getLocalizedMessage());
        }

        return suggestions;
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
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("complete() " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog("complete()", e.getLocalizedMessage());
        }

        return suggestions;
    }
    // </editor-fold>

   
    
    
    
   

    // <editor-fold defaultstate="collapsed" desc="List<Boletas> betweendate( String fieldstart, String start, String fieldend,String end)">
    public List<Boletas> betweendate(String fieldstart, String start, String fieldend, String end) {
        List<Boletas> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/betweendate/")
                    .queryParam("fieldstart", fieldstart)
                    .queryParam("start", start)
                    .queryParam("fieldend", fieldend)
                    .queryParam("end", end)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Boletas>>() {
                    });

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return suggestions;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="List<Boletas> betweendateUsingHours( String fieldstart, String start, String fieldend,String end)">
    public List<Boletas> betweendateUsingHours(String fieldstart, String start, String fieldend, String end) {
        List<Boletas> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/betweendateUsinghours/")
                    .queryParam("fieldstart", fieldstart)
                    .queryParam("start", start)
                    .queryParam("fieldend", fieldend)
                    .queryParam("end", end)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Boletas>>() {
                    });

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return suggestions;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<Boletas> betweendateWithFilter(String fieldstart, String start, String fieldend, String end)">
    /**
     *
     * @param fieldstart
     * @param start
     * @param fieldend
     * @param end
     * @return
     */
    public List<Boletas> betweendateWithFilter(String fieldstart, String start, String fieldend, String end, String fieldname, String value, String fieldtype) {
        List<Boletas> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/betweendateaditionalfilter/")
                    .queryParam("fieldstart", fieldstart)
                    .queryParam("start", start)
                    .queryParam("fieldend", fieldend)
                    .queryParam("end", end)
                    .queryParam("fieldname", fieldname)
                    .queryParam("value", value)
                    .queryParam("fieldtype", fieldtype)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Boletas>>() {
                    });

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return suggestions;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="List<Boletas> betweendateWithFilterUsingHours(String fieldstart, String start, String fieldend, String end)">
    /**
     *
     * @param fieldstart
     * @param start
     * @param fieldend
     * @param end
     * @return
     */
    public List<Boletas> betweendateWithFilterUsingHours(String fieldstart, String start, String fieldend, String end, String fieldname, String value, String fieldtype) {
        List<Boletas> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/betweendateaditionalfilterUsingHours/")
                    .queryParam("fieldstart", fieldstart)
                    .queryParam("start", start)
                    .queryParam("fieldend", fieldend)
                    .queryParam("end", end)
                    .queryParam("fieldname", fieldname)
                    .queryParam("value", value)
                    .queryParam("fieldtype", fieldtype)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Boletas>>() {
                    });

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return suggestions;
    }
    // </editor-fold>
    


//   // <editor-fold defaultstate="collapsed" desc="Integer  count(Boolean active)">

    /**
     * Consulta las boletas por departamento y el (estadoautoridad= or
     * estadoautoridad=)
     *
     * @param iddepartament
     * @param estadoautoridad1
     * @param estadoautoridad2
     * @return
     */
    public Integer count(Boolean active) {
        Integer total = 0;
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());

            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/count")
                            .queryParam("active", active);

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
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return total;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Integer countBetweendate(String fieldstart, String start, String fieldend, String end)">
    public Integer countBetweendate(String fieldstart, String start, String fieldend, String end) {
        Integer total = 0;
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());

            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/countbetweendate")
                            .queryParam("fieldstart", fieldstart)
                            .queryParam("start", start)
                            .queryParam("fieldend", fieldend)
                            .queryParam("end", end);

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
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return total;
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Integer countBetweendateUsingHours(String fieldstart, String start, String fieldend, String end)">
    public Integer countBetweendateUsingHours(String fieldstart, String start, String fieldend, String end) {
        Integer total = 0;
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());

            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/countbetweendateusignhours")
                            .queryParam("fieldstart", fieldstart)
                            .queryParam("start", start)
                            .queryParam("fieldend", fieldend)
                            .queryParam("end", end);

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
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return total;
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Integer countBetweendateWithFilter(String fieldstart, String start, String fieldend, String end, String fieldname, String value, String fieldtype)">
    public Integer countBetweendateWithFilter(String fieldstart, String start, String fieldend, String end,
            String fieldname, String value, String fieldtype) {
        Integer total = 0;
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());

            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/countbetweendateaditionalfilter")
                            .queryParam("fieldstart", fieldstart)
                            .queryParam("start", start)
                            .queryParam("fieldend", fieldend)
                            .queryParam("end", end)
                            .queryParam("fieldname", fieldname)
                            .queryParam("value", value)
                            .queryParam("fieldtype", fieldtype);

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
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return total;
    }

    // </editor-fold>
//
// <editor-fold defaultstate="collapsed" desc="Integer countBetweendateWithFilter(String fieldstart, String start, String fieldend, String end, String fieldname, String value, String fieldtype)">
    public Integer countBetweendateWithFilterUsingHours(String fieldstart, String start, String fieldend, String end,
            String fieldname, String value, String fieldtype) {
        Integer total = 0;
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());

            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/countbetweendateaditionalfilterusinghours")
                            .queryParam("fieldstart", fieldstart)
                            .queryParam("start", start)
                            .queryParam("fieldend", fieldend)
                            .queryParam("end", end)
                            .queryParam("fieldname", fieldname)
                            .queryParam("value", value)
                            .queryParam("fieldtype", fieldtype);

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
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return total;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="List<Boletas> findByEstadoUnidadAndEstadoAutoridad@QueryParam("estadounidad") String estadounidad,             @QueryParam("estadoautoridad") String estadoautoridad)">
    public List<Boletas> findByUserAndFieldType(String username,
            String field,
            String value, String fieldtype) {
        List<Boletas> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/findbbyuserandfield/")
                    .queryParam("user.username", username)
                    .queryParam("field", field)
                    .queryParam("value", value)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Boletas>>() {
                    });

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("findByEstadoUnidadAndEstadoAutoridad() " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog("findByEstadoUnidadAndEstadoAutoridad(()", e.getLocalizedMessage());
        }

        return suggestions;
    }

    // </editor-fold>
    
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="BooleanisAvailableBetweenDateHours(String query, String fieldstart, String start, String fieldend, String end)">
    public Boolean isAvailableBetweenDateHours(String query, String fieldstart, String start, String fieldend, String end
          ) {
     
       Boolean found =false;
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());

            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/isAvailableBetweenDateHours")
                            .queryParam("fieldstart", fieldstart)
                            .queryParam("start", start)
                            .queryParam("fieldend", fieldend)
                            .queryParam("end", end)
                            .queryParam("query", JmoordbDocument.encodeJson(query));
//                            .queryParam("query", query);

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

            Response response = invocationBuilder.get();
     
            if (response.getStatus() == 201) {
          
                String result= response.readEntity(String.class);
                if(result.equals("true")){
          
                    return true;
                }else{
           
                    return false;
                }
                
       

            }

            if (response.getStatus() == 400) {
                exception = new Exception(response.readEntity(String.class));
              
                return false;
            }

        } catch (Exception e) {
           
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return found;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="List<Boletas> availableBetweenDateHours(String query, String fieldstart, String start, String fieldend, String end)">
    public List<Boletas> availableBetweenDateHours(String query, String fieldstart, String start, String fieldend, String end
          ) {
       List<Boletas> suggestions = new ArrayList<>();
        try {

          

             Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/availableBetweenDateHours/")                    
                .queryParam("fieldstart", fieldstart)
                            .queryParam("start", start)
                            .queryParam("fieldend", fieldend)
                            .queryParam("end", end)
                            .queryParam("query", JmoordbDocument.encodeJson(query))
                  
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Boletas>>() {
                    });

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return suggestions ;
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
    }// </editor-fold>
    
     

 
       
// <editor-fold defaultstate="collapsed" desc=" List<Boletas> jsonQuery(@QueryParam("query") String query , @QueryParam("sort") String sort, @QueryParam("pagenumber") Integer pageNumber, @QueryParam("rowforpage") Integer rowForPage )">

  public  List<Boletas> jsonQuery( String query ,  String sort,
     Integer pageNumber,  Integer rowForPage ){
        List<Boletas> suggestions = new ArrayList<>();
        try {
 

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/jsonquery/")                    
                    .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort",JmoordbDocument.encodeJson(sort))
                    .queryParam("pagenumber", pageNumber)
                    .queryParam("rowforpage", rowForPage)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Boletas>>() {
                    });

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod()+ e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return suggestions;
    }
    // </editor-fold>   
  
  // <editor-fold defaultstate="collapsed" desc=" List<Boletas> jsonQueryWithoutPagination( @QueryParam("query") String query , @QueryParam("sort") String sort  ){">
   
   public List<Boletas> jsonQueryWithoutPagination( String query ,  String sort  ){
        List<Boletas> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/jsonquerywithoutpagination/")                    
                 .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort",JmoordbDocument.encodeJson(sort))
                  
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Boletas>>() {
                    });
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("lisfOfPage() " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog("lisfOfPage()", e.getLocalizedMessage());
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
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/boletas/countjsonquery")
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
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return total;
    }
    // </editor-fold>
    
    
    
    
    
    

}
