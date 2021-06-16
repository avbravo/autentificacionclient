/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;

import com.avbravo.autentificacionclient.entity.Country;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class CountryDataModel extends ListDataModel<Country> implements SelectableDataModel<Country> {

    public CountryDataModel() {
    }

    public CountryDataModel(List<Country> data) {
        super(data);
    }

    @Override
    public Country getRowData(String rowKey) {
        List<Country> list = (List<Country>) getWrappedData();

        for (Country countryo : list) {
            if (countryo.getIdcountry().equals(rowKey)) {
                return countryo;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Country country) {
        return country.getIdcountry();
    }

}
