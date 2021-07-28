/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.services;


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
public class UserServices implements Serializable {

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

// <editor-fold defaultstate="collapsed" desc="List<User> findAll()">
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget target = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/findall");

            GenericType<List<User>> data = new GenericType<List<User>>() {
            };

            userList = target.request(MediaType.APPLICATION_JSON).get(data);

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("findAll()" + e.getLocalizedMessage());
        }
        return userList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean add(User user)">
    public Boolean add(User user) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/add");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));

           
            if (response.getStatus() == 400) {
                 exception = new Exception(response.readEntity(String.class));
                return false;
            }
           user.setIduser(Integer.parseInt(response.readEntity(String.class)));
            return true;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("errort" + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Boolean update(User user)">
    public Boolean update(User user) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/update");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.entity(user, MediaType.APPLICATION_JSON));

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
// <editor-fold defaultstate="collapsed" desc="Boolean delete(User user)">

    public Boolean delete(User user) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/delete");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));

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

    // <editor-fold defaultstate="collapsed" desc="User findByUsername(String username) ">
    /**
     * consulta por codigo_pedido impresa
     *
     * @param codigo_
     * @return
     */
    public Optional<User> findByUsername(String username) {
        User user = new User();
        try {
        
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            user = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/search/")
                    .path("/{username}")
                    .resolveTemplate("username", username)
                    .request(MediaType.APPLICATION_JSON)
                    .get(User.class
                    );
            if(user == null || user.getIduser()== null || user.getUsername() == null || user.getUsername().isEmpty()){
              
                 return Optional.empty();
            }
      
            return Optional.of(user);
            //String result = FAIL;
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
              System.out.println("findBUsername() " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
          
        }

        return Optional.empty();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<User> complete( String query)">
    public List<User> complete(String query) {
        List<User> suggestions = new ArrayList<>();
        try {

            if (query == null || query.isEmpty()) {
                query = "{{complete}}";
            }
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/autocomplete/query/")
                    .path("/{nombre}")
                    .resolveTemplate("nombre", query)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<User>>() {
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

    
     // <editor-fold defaultstate="collapsed" desc="List<User> searchByIdDepartament(Integer iddepartament) ">
    public List<User> searchByIdDepartament(Integer iddepartament) {
        List<User> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/searchbyiddepartament/")
                    .path("/{iddepartament}")
                    .resolveTemplate("iddepartament", iddepartament)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<User>>() {
                    });

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return suggestions;
    }
    // </editor-fold>
     // <editor-fold defaultstate="collapsed" desc="List<User> searchJefeUnidadDelDepartament(Integer iddepartament) ">
    public List<User> searchJefeUnidadDelDepartament(Integer iddepartament) {
        List<User> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/searchjefeunidadepartament/")
                    .path("/{iddepartament}")
                    .resolveTemplate("iddepartament", iddepartament)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<User>>() {
                    });

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return suggestions;
    }
    // </editor-fold>
    
     // <editor-fold defaultstate="collapsed" desc="List<User>  searchAutoridad( Boolean isauthority)" >
    /**
     * Busca los usuarios que sean autoridad (isauthority = true
     * o que no sean autoridad isauthority = false)
     * Recuerde que es una lista embebida de muchos roles
     
     */
    public List<User>  searchAutoridad( Boolean isauthority) {
        List<User> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/searchautoridad/")
                    .path("/{isauthority}")
                    .resolveTemplate("isauthority", isauthority)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<User>>() {
                    });

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return suggestions;
    }
    // </editor-fold>
    
     // <editor-fold defaultstate="collapsed" desc="List<User>  searchAutoridad( Boolean isauthority)" >
    /**
     * Busca los usuarios que sean autoridad (isauthority = true
     * o que no sean autoridad isauthority = false)
     * Recuerde que es una lista embebida de muchos roles
     
     */
    public List<User>  searchRecursosHumanos( Boolean ishumanresourcesauthority) {
        List<User> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/searchrecursoshumanos/")
                    .path("/{ishumanresourcesauthority}")
                    .resolveTemplate("ishumanresourcesauthority", ishumanresourcesauthority)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<User>>() {
                    });

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }

        return suggestions;
    }
    // </editor-fold>
    
    
     
    
    
      // <editor-fold defaultstate="collapsed" desc="List<User> findbyIdepartamentIdrol(Integer iddepartament ,Integer idrol )">
 
    /**
     * 
     * @param iddepartament
     * @param idrol
     * @return 
     */
    
    public List<User> findbyIdepartamentIdrol(Integer iddepartament ,Integer idrol ) {  
       List<User> suggestions = new ArrayList<>();
        try {

           Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/findbyidepartamentandidrol/")
                    .queryParam("iddepartament", iddepartament)
                    .queryParam("idrol", idrol)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<User>>() {
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
    
    
    
      
    
  // <editor-fold defaultstate="collapsed" desc="List<User> findbyEmail(String email )">
 
    /**
     * 
     * @param iddepartament
     * @param idrol
     * @return 
     */
    
    public List<User> findbyEmail(String email ) {  
       List<User> suggestions = new ArrayList<>();
        try {

           Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/findbyemail/")
                    .queryParam("email", email)
                                        .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<User>>() {
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
    
    
    
    
    
       
// <editor-fold defaultstate="collapsed" desc=" List<User> jsonQuery(@QueryParam("query") String query , @QueryParam("sort") String sort, @QueryParam("pagenumber") Integer pageNumber, @QueryParam("rowforpage") Integer rowForPage )">

  public  List<User> jsonQuery( String query ,  String sort,
     Integer pageNumber,  Integer rowForPage ){
        List<User> suggestions = new ArrayList<>();
        try {
 

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/jsonquery/")                    
                    .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort",JmoordbDocument.encodeJson(sort))
                    .queryParam("pagenumber", pageNumber)
                    .queryParam("rowforpage", rowForPage)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<User>>() {
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
  
  // <editor-fold defaultstate="collapsed" desc=" List<User> jsonQueryWithoutPagination( @QueryParam("query") String query , @QueryParam("sort") String sort  ){">
   
   public List<User> jsonQueryWithoutPagination( String query ,  String sort  ){
        List<User> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/jsonquerywithoutpagination/")                    
                 .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort",JmoordbDocument.encodeJson(sort))
                  
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<User>>() {
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
                    = client.target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/countjsonquery")
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
