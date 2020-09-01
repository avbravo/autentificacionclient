/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Secondary;
import com.avbravo.jmoordb.pojos.UserInfo;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class Institution {

    @Id
    private Integer idinstitution;
    @Secondary
    private String institution;
    private String active;
  

    public Institution() {
    }

    public Institution(Integer idinstitution, String institution, String active) {
        this.idinstitution = idinstitution;
        this.institution = institution;
        this.active = active;
    }

    public Integer getIdinstitution() {
        return idinstitution;
    }

    public void setIdinstitution(Integer idinstitution) {
        this.idinstitution = idinstitution;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

   
   @Override
    public boolean equals(Object object) {
        if (!(object instanceof Institution)) {
            return false;
        }
      Institution other = (Institution) object;
        if ((this.idinstitution == null && other.idinstitution != null) || (this.idinstitution != null && !this.idinstitution.equals(other.idinstitution))) {
            return false;
        }
        return true;
    }

}
