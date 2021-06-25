/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.asistenciaclient.converter;

import com.avbravo.asistenciaclient.services.NotificationsServices;
import com.avbravo.autentificacionclient.entity.Notifications;
import java.util.Optional;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@FacesConverter(value = "notificationsConverter", managed = true)
public class NotificationsConverter implements Converter<Notifications> {

    @Inject
    private NotificationsServices notificationsServices;

    @Override
    public Notifications getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.isEmpty() || value.equals("null")) {
            return new Notifications();
        }
        try {
            Optional<Notifications> optional = notificationsServices.findByIdnotificationsboletas(Integer.parseInt(value));
            if (optional.isPresent()) {
                return optional.get();
            }
            return new Notifications();
        } catch (NumberFormatException e) {

            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error" + e.getLocalizedMessage(), "Not a valid country."));
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Notifications value) {
        if (value != null) {
            return String.valueOf(value.getIdnotifications());
        } else {
            return null;
        }
    }

}
