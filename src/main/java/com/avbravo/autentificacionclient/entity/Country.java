/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.entity;

import com.avbravo.jmoordb.anotations.Id;

/**
 *
 * @author avbravo
 */
public class Country {
    @Id
    private String idcountry;
    private String country;

    public Country() {
    }

    public Country(String idcountry, String country) {
        this.idcountry = idcountry;
        this.country = country;
    }

    public String getIdcountry() {
        return idcountry;
    }

    public void setIdcountry(String idcountry) {
        this.idcountry = idcountry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}
