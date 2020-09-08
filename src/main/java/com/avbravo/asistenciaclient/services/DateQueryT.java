/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.asistenciaclient.services;

import java.util.Date;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;

/**
 *
 * @author avbravo
 */
public class DateQueryT {
    
   @FormParam("field")
   String field;

   @FormParam("start")
   Date start;
   @FormParam("end")
   Date end;

   @HeaderParam("Content-Type")
   String contentType;

    public DateQueryT() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
   
   
   
}
