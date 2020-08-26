/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;

import com.avbravo.autentificacionclient.entity.Collectionincrementable;
import com.avbravo.autentificacionclient.services.CollectionincrementableServices;
import com.avbravo.jmoordb.util.JmoordbUtil;
import java.util.Optional;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@FacesConverter( forClass=Collectionincrementable.class, managed = true)
public class CollectionincrementableConverter implements Converter<Collectionincrementable> {
    @Inject
    CollectionincrementableServices collectionincrementableServices;

    @Override
    public Collectionincrementable getAsObject(FacesContext fc, UIComponent uic, String string) {
          Collectionincrementable collectionincrementable = new Collectionincrementable();
        try {
            if (string  == null || string.isEmpty()) {
            return null;
        }
             Optional<Collectionincrementable> optional = collectionincrementableServices.findByCollectionincrementablename(string);
                if (optional.isPresent()) {
                    collectionincrementable = optional.get();
                }
        } catch (Exception e) {
               JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return collectionincrementable;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Collectionincrementable t) {
     
       try{
            if (t == null) {

                return "";
            }

            if (t.getCollections()!= null) {

                return t.getCollections().toString();
            } else {

                //JmoordbUtil.warningDialog("No es valido el id ","");
            }

        } catch (Exception e) {
            JmoordbUtil.errorDialog(JmoordbUtil.nameOfMethod(), e.getLocalizedMessage());
        }
        return "";
    }

}
