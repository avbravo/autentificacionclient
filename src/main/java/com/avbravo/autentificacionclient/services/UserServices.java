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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
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

/**
 *
 * @author avbravo
 */
@Stateless
public class UserServices implements Serializable {
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
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);

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
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
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

            if (response.getStatus() == 400) {
                exception = new Exception(response.readEntity(String.class));
                return false;
            }

            return true;
        } catch (Exception e) {
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
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

            if (response.getStatus() == 400) {
                exception = new Exception(response.readEntity(String.class));
                return false;
            }

            return true;
        } catch (Exception e) {
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
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
javax.ws.rs.client.ClientBuilder cb = ClientBuilder.newBuilder();



 
            javax.ws.rs.client.Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            user = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/search/")
                    .path("/{username}")
                    .resolveTemplate("username", username)
                    .request(MediaType.APPLICATION_JSON)
                    .get(User.class
                    );
            if (user == null || user.getIduser() == null || user.getUsername() == null || user.getUsername().isEmpty()) {

                return Optional.empty();
            }

            return Optional.of(user);
            //String result = FAIL;
        } catch (Exception e) {
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
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
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
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
    public List<User> findbyEmail(String email) {
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
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
        }
        return suggestions;
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" List<User> jsonQuery(@QueryParam("query") String query , @QueryParam("sort") String sort, @QueryParam("pagenumber") Integer pageNumber, @QueryParam("rowforpage") Integer rowForPage )">
    public List<User> jsonQuery(String query, String sort,
            Integer pageNumber, Integer rowForPage) {
        List<User> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/jsonquery/")
                    .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort", JmoordbDocument.encodeJson(sort))
                    .queryParam("pagenumber", pageNumber)
                    .queryParam("rowforpage", rowForPage)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<User>>() {
                    });

        } catch (Exception e) {
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
        }

        return suggestions;
    }
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc=" List<User> jsonQueryWithoutPagination( @QueryParam("query") String query , @QueryParam("sort") String sort  ){">
    public List<User> jsonQueryWithoutPagination(String query, String sort) {
        List<User> suggestions = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                    .target(microservicesProducer.microservicesHost() + "/autentificacion/resources/user/jsonquerywithoutpagination/")
                    .queryParam("query", JmoordbDocument.encodeJson(query))
                    .queryParam("sort", JmoordbDocument.encodeJson(sort))
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<User>>() {
                    });
        } catch (Exception e) {
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
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
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
        }

        return total;
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
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
        }
        return listSource;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<String> extractEmailList(List<User> userList) ">
    /**
     * Extrae la lista de emails d
     *
     * @param userList
     * @return
     */
    public List<String> extractEmailList(List<User> userList) {
        List<String> emailList = new ArrayList<>();

        try {

            //Carga la lista 
            if (!userList.isEmpty()) {
                userList.forEach(u -> emailList.add(u.getEmail()));
            }

        } catch (Exception e) {
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
        }
        return emailList;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="List<String> extractUsernameList(List<User> userList) ">

    /**
     * Extrae la lista de username
     *
     * @param userList
     * @return
     */
    public List<String> extractUsernameList(List<User> userList) {
        List<String> emailList = new ArrayList<>();

        try {

            //Carga la lista 
            if (!userList.isEmpty()) {
                userList.forEach(u -> emailList.add(u.getUsername()));
            }

        } catch (Exception e) {
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
        }
        return emailList;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean foundInList(List<User> userList, User user)">
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
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
        }
        return false;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<User> findByProfileList(Integer applicativeId,List<Profile> profileList ,  Integer iddepartamentLoged)">
    /**
     * Genera la lista de usuarios en base a un List de profile y descarta los
     * profile que no pertenezcan al applicativeId
     *
     * @param applicativeId : El applicativo principal
     * @param profileList: Lista de profiles a realizar la busqueda
     * @param iddepartament: Departamento actual del usuario logeado
     * @return
     */
    public List<User> findByProfileList(Integer applicativeId, List<Profile> profileList, Integer iddepartamentLoged) {
        List<User> userList = new ArrayList<>();
        try {
            Document sort = new Document("username", -1);
            for (Profile p : profileList) {

                List<User> list = queryElemMatch(p.getIdapplicative(), p.getIddepartament(), p.getIdrole(), Boolean.TRUE);
                if (list == null || list.isEmpty() || list.size() == 0) {

                } else {

//                        for(User u:list){
//                            System.out.println("username: "+u.getUsername() );
//                            for(Profile px:u.getProfile()){
//                                System.out.println(" idapplicative "+px.getIdapplicative() + " idrole "+px.getIdrole() +" iddepartament "+px.getIddepartament() + " active "+px.getActive());
//                            }
//                        }
//                        System.out.println("----------------------voy a quitar duplicados-----------------------------------------");
                    userList = sinDuplicados(userList, list);

                }
            }
        } catch (Exception e) {
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
        }
        return userList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<User>  List<User> queryElemMatch (@QueryParam("idapplicative") Integer idapplicative, @QueryParam("iddepartament") Integer iddepartament, @QueryParam("idrole") Integer idrole, @QueryParam("active") Boolean active)">
    /**
     * Procesa una consulta en los profile Es una busqueda especial que requiere
     * elemMatch
     *
     * @param idapplicative : 0: Se ignora >0: Procesa ese valor
     * @param iddepartament 0: Se ignora >0: Procesa ese valor
     * @param idrole 0: Se ignora >0: Procesa ese valor
     * @param active
     * @return
     */
    public List<User> queryElemMatch(Integer idapplicative, Integer iddepartament, Integer idrole, Boolean active) {
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
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
        }

        return suggestions;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="haveProfile(Integer applicativeId,List<Profile> profileList, User user,Profile profile) ">
    /**
     * Verifica si el user que se paso y el profile coindicen con los perfiles
     * que tiene el usuario de una lista Por ejemplo si deseo conocer si un
     * usuario es autoridad es util para esos casos
     *
     * @param applicativeId
     * @param profileList
     * @param profile
     * @return
     */
    public Boolean haveProfile(Integer applicativeId, List<Profile> profileList, User user, Profile profile) {
        Boolean found = false;
        try {
            List<User> list = findByProfileList(applicativeId, profileList, profile.getIddepartament());

            if (list == null || list.isEmpty() || list.size() == 0) {

            } else {
                for (User u : list) {
                    if (u.getUsername().equals(user.getUsername())) {
                        for (Profile p : u.getProfile()) {
                            if (profile.getIdapplicative().equals(p.getIdapplicative())
                                    && profile.getIdrole().equals(p.getIdrole())) {
                                found = true;
                                break;

                            }
                        }
                    }

                }
            }

        } catch (Exception e) {
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
        }
        return found;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="processAndChange(Integer idapplicative, Integer idrole, List<Profile> profileList, Boolean isChange)">
    /**
     *
     * Procesa la lista y actualiza o crea un profile para este sistema
     */
    public List<Profile> processAndChangeProfile(Integer idapplicative, Integer iddepartament, Integer idrole, List<Profile> profileList, Boolean isChange) {
        try{
        Integer pos = 0;
        Boolean found = false;
        for (Profile p : profileList) {
            if (p.getIdapplicative().equals(idapplicative) && p.getIdrole().equals(idrole)) {
                found = true;
                p.setActive(isChange);
                profileList.set(pos, p);

            }
            pos++;
        }
        if (isChange && !found) {
            Profile profile = new Profile();
            profile.setIdprofile(maxValueProfile(profileList) + 1);
            profile.setIdapplicative(idapplicative);
            profile.setIddepartament(iddepartament);
            profile.setIdrole(idrole);
            profile.setActive(Boolean.TRUE);
            profileList.add(profile);
        }
         } catch (Exception e) {
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
        }
        return profileList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean haveRole(Integer idrole, User user) ">
    public Boolean haveRole(Integer idApplicative, Integer idrole, User user) {
        try {

            for (Profile p : user.getProfile()) {

                if (p.getIdapplicative().equals(idApplicative)
                        && p.getIdrole().equals(idrole) && p.getActive()) {

                    return true;
                }
            }
        } catch (Exception e) {
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
        }
        return false;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Integer maxValueProfile(List<Profile> profileList)">
    /**
     * Encuentra el valor maximp de idprofile
     *
     * @param profileList
     * @return
     */
    public Integer maxValueProfile(List<Profile> profileList) {
        try {
            if (profileList == null || profileList.isEmpty()) {
                return 0;
            }
            // then
            Profile max = profileList
                    .stream()
                    .max(Comparator.comparing(Profile::getIdprofile))
                    .orElseThrow(NoSuchElementException::new);

            return max.getIdprofile();
        } catch (Exception e) {
            exception = loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, false);
        }
        return 0;
    }

    // </editor-fold>
}
