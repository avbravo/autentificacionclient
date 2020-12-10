/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.services;

import com.avbravo.asistenciaclient.entity.Boletas;
import com.avbravo.autentificacionclient.entity.User;
import com.avbravo.jmoordb.util.JmoordbUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@SessionScoped
public class EmailServices implements Serializable {

    


    // <editor-fold defaultstate="collapsed" desc=" inject">
    @Inject
    UserServices userServices;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" set/get()">
   

 

// </editor-fold>
    @PostConstruct
    public void init() {
      

    }


  

    // <editor-fold defaultstate="collapsed" desc="List<String> generateList(Boletas boletas, Boolean isJefeUnidad, Boolean isAutoridad, Boolean isRecursosHumanos, String msgJefeUnidad,           String msgAutoridad, String msgRecursos) ">
    public List<String> generateList(Boletas boletas, Boolean isJefeUnidad, Boolean isAutoridad, Boolean isRecursosHumanos, String msgJefeUnidad,
            String msgAutoridad, String msgRecursos) {
        List<String> emailList = new ArrayList<>();
        List<User> list = new ArrayList<>();
        try {
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
            if(!list.isEmpty()){
               list.forEach(u->emailList.add(u.getEmail()));
            }
          
         
        } catch (Exception e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
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
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
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

             
                userAutoridadList.forEach(u->{
                   if(!list.contains(u)){
                       list.add(u);
                          
                   }
                });
            }

        } catch (Exception e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
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


                userRecursosHumanosList.forEach(u->{
                if(!list.contains(u)){

                    list.add(u);
                }
                }
                );
                
            }

        } catch (Exception e) {
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
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return listSource;
    }
// </editor-fold>
    
   
   
}
