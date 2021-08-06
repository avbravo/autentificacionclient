/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.services;


import com.avbravo.autentificacionclient.entity.Profile;
import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.autentificacionclient.producer.AuthentificationProducer;
import com.avbravo.autentificacionclient.producer.MicroservicesProducer;
import com.avbravo.jmoordb.util.JmoordbDocument;
import com.avbravo.jmoordb.util.JmoordbUtil;
import com.mongodb.client.model.Filters;
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
import org.bson.Document;
import org.bson.conversions.Bson;

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
     // <editor-fold defaultstate="collapsed" desc="List<User> searchJefeUnidadDelDepartament(Integer idapplicative,Integer iddepartament, Integer idrolejefeunidad) ">
    public List<User> searchJefeUnidadDelDepartament(Integer idapplicative, Integer iddepartament, Integer idrolejefeunidad) {
        List<User> suggestions = new ArrayList<>();
        try {

      
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/searchjefeunidadepartament/")
                     .queryParam("idapplicative", idapplicative)
                    .queryParam("iddepartament", iddepartament)
                    .queryParam("idrole", idrolejefeunidad)
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
    
     // <editor-fold defaultstate="collapsed" desc="List<User>  searchAutoridad(Integer idapplicative, Boolean isauthority)" >
    /**
     * Busca los usuarios que sean autoridad (isauthority = true
     * o que no sean autoridad isauthority = false)
     * Recuerde que es una lista embebida de muchos roles
     
     */
    public List<User>  searchAutoridad(Integer idapplicative, Boolean isauthority) {
        List<User> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/searchautoridad/")
                   .queryParam("idapplicative", idapplicative)
                    .queryParam("isauthority", isauthority)
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
    
     // <editor-fold defaultstate="collapsed" desc="List<User>  searchAutoridad( Integer idapplicative,Boolean isauthority)" >
    /**
     * Busca los usuarios que sean autoridad (isauthority = true
     * o que no sean autoridad isauthority = false)
     * Recuerde que es una lista embebida de muchos roles
     
     */
    public List<User>  searchRecursosHumanos( Integer idapplicative,Boolean ishumanresourcesauthority) {
        List<User> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/searchrecursoshumanos/")
                     .queryParam("idapplicative", idapplicative)
                    .queryParam("ishumanresourcesauthority", ishumanresourcesauthority)
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
    
    
     
    
    
    // <editor-fold defaultstate="collapsed" desc="List<User> findbyIdApplicativeIdepartamentIdrole)">
   /**
    * Devuelve la lista de usuarios que pertenecen a un applicative, departamento y rol
    * @param idapplicative
    * @param iddepartament
    * @param idrol
    * @return 
    */
    public List<User> findbyIdApplicativeIdepartamentIdrole(Integer idapplicative, Integer iddepartament, Integer idrole) {
        List<User> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/findbyidapplicativeidepartamentandidrole/")
                    .queryParam("idapplicative", idapplicative)
                    .queryParam("iddepartament", iddepartament)
                    .queryParam("idrole", idrole)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<User>>() {
                    });
            //String result = FAIL;
        } catch (Exception e) {
                exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return suggestions;
    }

    // </editor-fold>
    
    
      
    
    
    
    // <editor-fold defaultstate="collapsed" desc="List<User> findbyIdApplicativeIdrole)">
   /**
    * Devuelve la lista de usuarios que pertenecen a un applicative, departamento y rol
    * @param idapplicative
    * @param iddepartament
    * @param idrol
    * @return 
    */
    public List<User> findbyIdApplicativeIdrole(Integer idapplicative,  Integer idrole) {
        List<User> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/findbyidapplicativeidrole/")
                    .queryParam("idapplicative", idapplicative)
                     .queryParam("idrole", idrole)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<User>>() {
                    });
            //String result = FAIL;
        } catch (Exception e) {
                exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod() + e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
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
  // <editor-fold defaultstate="collapsed" desc=" List<User> jsonQueryMultiple( @QueryParam("query") List<String> query ){">
   
   public List<User> jsonQueryMultipleWithoutPagination( List<String> queryList  ){
        List<User> suggestions = new ArrayList<>();
        try {
List<String> queryEncode = new ArrayList<>();
queryList.forEach(s -> {
    queryEncode.add( JmoordbDocument.encodeJson(s));
            });
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/jsonquerymultiplewithoutpagination/")                    
                 .queryParam("query", queryEncode)
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
      
    
    
     
    
    
    // <editor-fold defaultstate="collapsed" desc=" private List<User> locateJefeUnidadDelDepartament(Integer idapplicative,Integer iddepartament,String username, String mmsgJefeUnidad, Boolean showMessage)">
    private List<User> locateJefeUnidadDelDepartament(Integer idapplicative,Integer iddepartament,Integer idrole,String username, String mmsgJefeUnidad, Boolean showMessage) {
        List<User> list = new ArrayList<>();
        try {
            List<User> userJefeUnidad =searchJefeUnidadDelDepartament(idapplicative,iddepartament,idrole);
            if (userJefeUnidad == null || userJefeUnidad.isEmpty()) {
                if(showMessage ){
                    JmoordbUtil.warningMessage(mmsgJefeUnidad);
                }
                
            } else {
                userJefeUnidad.stream().filter(u -> (!username.equals(u.getUsername()))).forEachOrdered(u -> {
                    list.add(u);

                });
            }
        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod()+ e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" private List<User> locateAutoridadInteger idapplicative,String msgAutoridad, Boolean showMessage) ">

 private List<User> locateAutoridad(Integer idapplicative,String msgAutoridad, Boolean showMessage) {
        List<User> list = new ArrayList<>();
        try {
            List<User> userAutoridadList = searchAutoridad(idapplicative,Boolean.TRUE);

            if (userAutoridadList == null || userAutoridadList.isEmpty()) {
               if(showMessage ){
                    JmoordbUtil.warningMessage(msgAutoridad);
               }
               
            } else {

             
                userAutoridadList.forEach(u->{
                   if(!list.contains(u)){
                       list.add(u);
                          
                   }
                });
            }

        } catch (Exception e) {
           exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod()+ e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>
 
 // <editor-fold defaultstate="collapsed" desc="private List<User> locateIdApplicativeIdepartamentIdrole(Integer idapplicative,Integer iddepartament, Integer idrole, String msg, Boolean showMessage)">
    /**
     * Busca por ese rol si hay usuarois
     * @param idapplicative
     * @param iddepartament
     * @param idrole
     * @param msg
     * @return 
     */
    private List<User> locateIdApplicativeIdepartamentIdrole(Integer idapplicative,Integer iddepartament, Integer idrole, String msg, Boolean showMessage) {
        List<User> list = new ArrayList<>();
        try {
            List<User> userRecursosHumanosList = findbyIdApplicativeIdepartamentIdrole( idapplicative, iddepartament, idrole);

            if (userRecursosHumanosList == null || userRecursosHumanosList.isEmpty()) {
                if(showMessage){
                    JmoordbUtil.warningMessage(msg);
                }
                
            } else {


                userRecursosHumanosList.forEach(u->{
                if(!list.contains(u)){

                    list.add(u);
                }
                }
                );
                
            }

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod()+ e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>
 
    
       // <editor-fold defaultstate="collapsed" desc="private List<User> locateRecursosHumanos(Integer idapplicative,String msgRecursos, Boolean showMessage)">
    private List<User> locateRecursosHumanos(Integer idapplicative,String msgRecursos, Boolean showMessage) {
        List<User> list = new ArrayList<>();
        try {
            List<User> userRecursosHumanosList = searchRecursosHumanos(idapplicative,Boolean.TRUE);

            if (userRecursosHumanosList == null || userRecursosHumanosList.isEmpty()) {
                if(showMessage){
                     JmoordbUtil.warningMessage(msgRecursos);
                }
               
            } else {


                userRecursosHumanosList.forEach(u->{
                if(!list.contains(u)){

                    list.add(u);
                }
                }
                );
                
            }

        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod()+ e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
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
            listToAdd.forEach(u->{
              
                if(!listSource.contains(u)){
                    listSource.add(u);

                }
              }
            );


        } catch (Exception e) {
           exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod()+ e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return listSource;
    }
// </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="List<User> generateUserList(Integer idapplicative,String username, Boolean isJefeUnidad, Boolean isAutoridad, Boolean isRecursosHumanos, String msgJefeUnidad, String msgAutoridad, String msgRecursos), List<Profile> profileList, Boolean showMessage) ">
    /**
     * Genera la lista de usuarios sin duplicados en base a los roles
     * @param idapplicative
     * @param iddepartament
     * @param username
     * @param isJefeUnidad
     * @param isAutoridad
     * @param isRecursosHumanos
     * @param msgJefeUnidad
     * @param msgAutoridad
     * @param msgRecursos
     * @param idRolesList
     * @return 
     */
    public List<User> generateUserList(Integer idapplicative,Integer iddepartament,Integer idrolejefeunidad,String username, Boolean isJefeUnidad, Boolean isAutoridad, Boolean isRecursosHumanos, String msgJefeUnidad,
            String msgAutoridad, String msgRecursos, List<Profile> profileList, Boolean showMessage) {
       
        List<User> list = new ArrayList<>();
        try {
            if (isJefeUnidad) {
                
                       list.addAll(locateJefeUnidadDelDepartament(idapplicative,iddepartament,idrolejefeunidad,username, msgJefeUnidad,showMessage));
              }
            if (isAutoridad) {

                list = sinDuplicados(list, locateAutoridad(idapplicative,msgAutoridad,showMessage));
            }
            if (isRecursosHumanos) {

                list = sinDuplicados(list, locateRecursosHumanos(idapplicative,msgRecursos,showMessage));
              
            }
            //Verifica los otros roles
            if(profileList == null ||profileList.isEmpty() ||profileList.size()==0 ){
                
            }else{
                for(Profile profile:profileList){
                     list = sinDuplicados(list, locateIdApplicativeIdepartamentIdrole(profile.getIdapplicative(), profile.getIddepartament(), profile.getIdrole(),"rol "+profile.getIdrole(),showMessage));
                }
            }
            
          
          
         
        } catch (Exception e) {
             exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod()+ e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>
    
     // <editor-fold defaultstate="collapsed" desc="List<String> extractEmailList(List<User> userList) ">
    /**
     * Extrae la lista de emails d
     * @param userList
     * @return 
     */
    public List<String> extractEmailList(List<User> userList) {
        List<String> emailList = new ArrayList<>();
        
        try {
           
            
            //Carga la lista 
            if(!userList.isEmpty()){
               userList.forEach(u->emailList.add(u.getEmail()));
            }
          
         
        } catch (Exception e) {
           exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod()+ e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return emailList;
    }
// </editor-fold>
     // <editor-fold defaultstate="collapsed" desc="List<String> extractUsernameList(List<User> userList) ">
    /**
     * Extrae la lista de username
     * @param userList
     * @return 
     */
    public List<String> extractUsernameList(List<User> userList) {
        List<String> emailList = new ArrayList<>();
        
        try {
           
            
            //Carga la lista 
            if(!userList.isEmpty()){
               userList.forEach(u->emailList.add(u.getUsername()));
            }
        
         
        } catch (Exception e) {
         exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod()+ e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return emailList;
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
    public Boolean foundInList(List<User> userList, User user) {
        List<User> list = new ArrayList<>();
        try {
         
              return userList.contains(user);
              


        } catch (Exception e) {
            exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod()+ e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="List<User> findByProfileList(Integer applicativeId,List<Profile> profileList ,  Integer iddepartamentLoged)">
/**
 * Genera la lista de usuarios en base a un List de profile y descarta los profile que no pertenezcan al applicativeId
 * @param applicativeId : El applicativo principal
 * @param profileList: Lista de profiles a realizar la busqueda
 * @param iddepartament: Departamento actual del usuario logeado
 * @return 
 */
    
    public List<User> findByProfileListOLD(Integer applicativeId, List<Profile> profileList ,  Integer iddepartamentLoged){
        List<User> userList = new ArrayList<>();
        try {
             Document sort = new Document("username", -1);
                for (Profile p : profileList) {
                    Document query = new Document();
                    Bson filter = Filters.and(Filters.eq("profile.idapplicative", p.getIdapplicative()),
                            Filters.eq("profile.idrole", p.getIdrole()), Filters.eq("profile.active", p.getActive()));

                    /**
                     * Crea el filtro por profile.getIddepartament()
                     * -1: No aplica el filtro por departamento solo busca por idapplicative e idrol 
                     *  0: Indica que debe usarse el valor iddepartamentLoged que debe contener el departamento usado en el controller
                     * a0: Indica que se debe usar el departamento que se asignao en profile.getIddepatament()
                     */
                    if (p.getIddepartament() == -1) {

                    } else {
                        if (p.getIddepartament() == 0) {
                            filter = Filters.and(filter, Filters.eq("profile.iddepartament", iddepartamentLoged));
                        } else {
                            filter = Filters.and(filter, Filters.eq("profile.iddepartament", p.getIddepartament()));
                        }

                    }
                    System.out.println("==============================================");
                    System.out.println(".... filter: "+filter.toString());
                    List<User> list = jsonQueryWithoutPagination(JmoordbDocument.bsonToJson(filter), JmoordbDocument.documentToJson(sort));
                    if (list == null || list.isEmpty() || list.size() == 0) {
                      
                    } else {
                        /**
                         * Elimina los roles que no son del applicative
                         */
                      Integer count=0;
                        for(User u1:list){
                              List<Profile> lpr= new ArrayList<>();
                              u1.getProfile().stream().filter(p1 -> (p1.getIdapplicative().equals(applicativeId) && p1.getActive())).forEachOrdered(p1 -> {
                                  lpr.add(p1);
                          });
                            list.get(count).setProfile(lpr);
                            count++;
                        }
                        System.out.println("---------------------------------------------------------------");
                        for(User u:list){
                            System.out.println("username: "+u.getUsername() );
                            for(Profile px:u.getProfile()){
                                System.out.println(" idapplicative "+px.getIdapplicative() + " idrole "+px.getIdrole() +" iddepartament "+px.getIddepartament() + " active "+px.getActive());
                            }
                        }
                        System.out.println("---------------------------------------------------------------");
                        userList = sinDuplicados(userList, list);
                        
                        
                    
                    }
                }
        } catch (Exception e) {
             exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod()+ e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return userList;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="List<User> findByProfileList(Integer applicativeId,List<Profile> profileList ,  Integer iddepartamentLoged)">
/**
 * Genera la lista de usuarios en base a un List de profile y descarta los profile que no pertenezcan al applicativeId
 * @param applicativeId : El applicativo principal
 * @param profileList: Lista de profiles a realizar la busqueda
 * @param iddepartament: Departamento actual del usuario logeado
 * @return 
 */
    
    public List<User> findByProfileList(Integer applicativeId, List<Profile> profileList ,  Integer iddepartamentLoged){
        List<User> userList = new ArrayList<>();
        try {
             Document sort = new Document("username", -1);
                for (Profile p : profileList) {
                  
                    List<User> list = queryElemMatch(p.getIdapplicative(),p.getIddepartament(),p.getIdrole(),Boolean.TRUE);
                    if (list == null || list.isEmpty() || list.size() == 0) {
                      
                    } else {
                      
                     
                        System.out.println("-----------------------IMPRIMIRE LA LISTA----------------------------------------");
                        for(User u:list){
                            System.out.println("username: "+u.getUsername() );
                            for(Profile px:u.getProfile()){
                                System.out.println(" idapplicative "+px.getIdapplicative() + " idrole "+px.getIdrole() +" iddepartament "+px.getIddepartament() + " active "+px.getActive());
                            }
                        }
                        System.out.println("----------------------voy a quitar duplicados-----------------------------------------");
                        userList = sinDuplicados(userList, list);
                        
                        
                    
                    }
                }
        } catch (Exception e) {
             exception = new Exception(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger, JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println(JmoordbUtil.nameOfMethod()+ e.getLocalizedMessage());
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return userList;
    }
    // </editor-fold>
    
    
    
    
     // <editor-fold defaultstate="collapsed" desc="List<User>  List<User> queryElemMatch (@QueryParam("idapplicative") Integer idapplicative, @QueryParam("iddepartament") Integer iddepartament, @QueryParam("idrole") Integer idrole, @QueryParam("active") Boolean active)">
    /**
     * Procesa una consulta en los profile 
     * Es una busqueda especial que requiere elemMatch
     * @param idapplicative :
     *                       0: Se ignora
     *                      >0: Procesa ese valor
     * @param iddepartament
     *                       0: Se ignora
     *                      >0: Procesa ese valor
     * @param idrole
     *                       0: Se ignora
     *                      >0: Procesa ese valor
     * @param active
     * @return 
     */
    public List<User>   queryElemMatch (Integer idapplicative, Integer iddepartament,Integer idrole, Boolean active){
        List<User> suggestions = new ArrayList<>();
        try {

      
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/queryelemmatch/")
                     .queryParam("idapplicative", idapplicative)
                    .queryParam("iddepartament", iddepartament)
                    .queryParam("idrole", idrole)
                    .queryParam("active", active)
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
    
    
}
