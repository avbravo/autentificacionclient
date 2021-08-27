/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.converter;


import com.avbravo.jmoordb.util.JmoordbUtil;
import java.text.DateFormat;
import java.time.LocalTime;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;

/**
 *
 * @author IsraelHenry
 */
@Named(value = "hourConverter")
@RequestScoped
public class HourConverter extends DateTimeConverter {

    /**
     * Creates a new instance of ConverterHoras
     *
     * @param context
     * @param component
     * @param submittedValue
     *
     * @return
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        try {

            DateFormat parser = null;
            Object returnValue = null;
            LocalTime tiempo;
            String hora;
            String minutos;
            if (context == null || component == null) {
                throw new NullPointerException();
            }
            if (submittedValue == null || submittedValue.isEmpty()) {

                return null;
            }
            submittedValue = submittedValue.toUpperCase().trim();
submittedValue =submittedValue.replace("A. M.", "AM");
submittedValue =submittedValue.replace("P. M.", "PM");
            
            
            submittedValue =submittedValue.toUpperCase();
            if (submittedValue.contains("AM") || submittedValue.contains("PM")) {
                String tiempoAMPM[] = submittedValue.split(" ");
                String horasMinutos[] = tiempoAMPM[0].split(":");

                hora = horasMinutos[0];
                minutos = horasMinutos[1];
                if (hora.equals("12") && tiempoAMPM[1].equals("AM")) {
                    hora = "00";
                } else if (tiempoAMPM[1].equals("PM") && !hora.equals("12")) {
                    int horaMilitar = Integer.parseInt(hora) + 12;
                    hora = String.valueOf(horaMilitar);
                }
//        DateTimeConverter a = new DateTimeConverter();
//        return a.getAsObject(context, component, hora + ":" + minutos);
//            return returnValue;

            } else {
                
                String horasMinutos[] = submittedValue.split(":");
                hora = horasMinutos[0];
                minutos = horasMinutos[1];

            }
            tiempo = LocalTime.of(Integer.parseInt(hora), Integer.parseInt(minutos));
            return tiempo;
        } catch (Exception e) {
           JmoordbUtil.errorMessage("La hora no es válida");
            return null;
        }

//        return hora+":"+minutos;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            LocalTime tiempo = (LocalTime) value;
            String amPM = "AM";
            int horas = tiempo.getHour();
            int minutos = tiempo.getMinute();
            if (horas > 12) {
                horas = horas - 12;
                amPM = "PM";
            } else if (horas == 0) {
                horas = 12;
            } else if (horas == 12) {
                amPM = "PM";
            }

            String horasFinales;
            String minutosFinales;
            horasFinales = (horas < 10) ? "0" + horas : String.valueOf(horas);
            minutosFinales = (minutos < 10) ? "0" + minutos : String.valueOf(minutos);
            return horasFinales + ":" + minutosFinales + " " + amPM;
        } catch (Exception e) {
          JmoordbUtil.errorMessage("La hora no es válida");
            return "";
        }
//        return String.valueOf((LocalTime) value);
//        return a.getAsString(context, component, value);
    }

}
